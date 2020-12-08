package rusha.x.recipe.list

import androidx.lifecycle.MutableLiveData
import rusha.x.BaseViewModel
import rusha.x.MainApi
import rusha.x.RxJavaSchedulers
import rusha.x.SingleLiveEvent
import rusha.x.recipe.Recipe
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(
    private val api: MainApi,
    private val schedulers: RxJavaSchedulers
) : BaseViewModel() {

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