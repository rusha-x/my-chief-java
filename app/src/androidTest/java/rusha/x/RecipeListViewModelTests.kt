package rusha.x

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import rusha.x.recipe.Recipe
import rusha.x.recipe.createOrEdit.CreateOrEditRecipe
import rusha.x.recipe.list.RecipeListViewModel

@RunWith(AndroidJUnit4::class)
class RecipeListViewModelTests {

    @UiThreadTest
    @Test
    fun loadRecipesOnInit() {

        val expectedRecipes = listOf(
            Recipe(
                id = 1,
                name = "Test",
                description = "Test Test",
                ingredients = listOf()
            )
        )

        di = DI {
            bind<MainApi>() with singleton {
                object : MainApi {
                    override fun createOrEditRecipe(body: CreateOrEditRecipe): Single<Unit> {
                        throw AssertionError()
                    }
                    override fun allRecipe(): Single<List<Recipe>> {
                        return Single.just(expectedRecipes)
                    }
                }
            }
            bind<RxJavaSchedulers>() with singleton {
                RxJavaSchedulers(
                    subscribe = Schedulers.trampoline(),
                    observe = Schedulers.trampoline()
                )
            }
        }

        val viewModel = RecipeListViewModel()
        viewModel.onResume()

        Assert.assertEquals(viewModel.recipesLiveData.value, expectedRecipes)
    }

    @UiThreadTest
    @Test
    fun loadRecipesOnRefresh() {

        val expectedRecipess = listOf(
            Recipe(
                id = 2,
                name = "Shpack",
                description = "Shpack Shpack",
                ingredients = listOf()
            )
        )

        di = DI {
            bind<MainApi>() with singleton {
                object : MainApi {
                    override fun createOrEditRecipe(body: CreateOrEditRecipe): Single<Unit> {
                        throw AssertionError()
                    }

                    override fun allRecipe(): Single<List<Recipe>> {
                        return Single.just(expectedRecipess)
                    }
                }
            }
            bind<RxJavaSchedulers>() with singleton {
                RxJavaSchedulers(
                    subscribe = Schedulers.trampoline(),
                    observe = Schedulers.trampoline()
                )
            }
        }
        val viewModel = RecipeListViewModel()
        viewModel.onRefresh()

        Assert.assertEquals(viewModel.recipesLiveData.value, expectedRecipess)
    }

    @UiThreadTest
    @Test
    fun loadRecipesOnClick() {

        di = DI {}
        val viewModel = RecipeListViewModel()
        var wasToEditRecipeCalled = false
        viewModel.goToEditRecipe.observe(mockLifecycleOwner(), Observer {
            wasToEditRecipeCalled = true
        })

        viewModel.onAddRecipeClick()

        Assert.assertEquals(wasToEditRecipeCalled, true)
    }

    @UiThreadTest
    @Test
    fun loadAddRecipesOnClick() {

        val expectedRecipe = Recipe(
            id = 4,
            name = "Shpack",
            description = "Shpack Shpack",
            ingredients = listOf()
        )

        di = DI {}
        val viewModel = RecipeListViewModel()
        viewModel.onRecipeClick(expectedRecipe)

        Assert.assertEquals(viewModel.goToRecipeDetails.value, expectedRecipe)
    }

    private fun mockLifecycleOwner(): LifecycleOwner {
        val owner: LifecycleOwner = mock(LifecycleOwner::class.java)
        val lifecycle = LifecycleRegistry(owner)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        `when`(owner.lifecycle).thenReturn(lifecycle)
        return owner
    }
}