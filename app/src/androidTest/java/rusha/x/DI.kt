package rusha.x

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import rusha.x.recipe.Recipe
import rusha.x.recipe.createOrEdit.CreateOrEditRecipe

val expectedRecipes = listOf(
    Recipe(
        id = 1,
        name = "Test",
        description = "Test Test",
        ingredients = listOf()
    )
)

@Module
class MockAppModule : AppModule() {

    @Provides
    override fun rxJavaSchedulers() = RxJavaSchedulers(
        subscribe = Schedulers.trampoline(),
        observe = Schedulers.trampoline()
    )

    @Provides
    override fun mainApi(): MainApi {
        return object : MainApi {
            override fun createOrEditRecipe(body: CreateOrEditRecipe): Single<Unit> {
                throw AssertionError()
            }

            override fun allRecipe(): Single<List<Recipe>> {
                return Single.just(expectedRecipes)
            }
        }
    }
}