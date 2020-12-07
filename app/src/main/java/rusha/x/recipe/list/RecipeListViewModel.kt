package rusha.x.recipe.list

import androidx.lifecycle.MutableLiveData
import org.kodein.di.instance
import rusha.x.*
import rusha.x.recipe.Recipe

class RecipeListViewModel : BaseViewModel() {
    private val api by di.instance<MainApi>()
    private val schedulers by di.instance<RxJavaSchedulers>()

    val recipesLiveData = MutableLiveData<List<Recipe>>(emptyList())
    val isRefreshingLiveData = MutableLiveData<Boolean>()
    val goToEditRecipe = SingleLiveEvent<Nothing>()
    val goToRecipeDetails = SingleLiveEvent<Recipe>()

    fun onRefresh() {
        updateRecipes()
    }

    fun onResume() {
        updateRecipes()
    }

    private fun updateRecipes() {
        isRefreshingLiveData.value = true
        api.allRecipe()
            .subscribeOn(schedulers.subscribe)
            .observeOn(schedulers.observe)
            .managedSubscribe { allRecipes ->
                recipesLiveData.value = allRecipes
                isRefreshingLiveData.value = false
            }
    }

    fun onAddRecipeClick() {
        goToEditRecipe.call()
    }

    fun onRecipeClick(recipe: Recipe) {
        goToRecipeDetails.value = recipe
    }
}