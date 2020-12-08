package rusha.x

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import rusha.x.recipe.Recipe

@RunWith(AndroidJUnit4::class)
class RecipeListViewModelTests {

    @UiThreadTest
    @Test
    fun loadRecipesOnInit() {
        val viewModel = di().recipeListViewModel()
        viewModel.onResume()

        Assert.assertEquals(viewModel.recipesLiveData.value, expectedRecipes)
    }

    @UiThreadTest
    @Test
    fun loadRecipesOnRefresh() {
        val viewModel = di().recipeListViewModel()
        viewModel.onRefresh()

        Assert.assertEquals(viewModel.recipesLiveData.value, expectedRecipes)
    }

    @UiThreadTest
    @Test
    fun loadRecipesOnClick() {

        val viewModel = di().recipeListViewModel()
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

        val viewModel = di().recipeListViewModel()
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

    private fun di(): AppComponent {
        return DaggerAppComponent.builder().appModule(MockAppModule()).build()
    }
}