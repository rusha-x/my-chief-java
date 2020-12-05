package rusha.x.recipe

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.kodein.di.instance
import rusha.x.BaseViewModel
import rusha.x.MainApi
import rusha.x.SingleLiveEvent
import rusha.x.di

class RecipeListViewModel : BaseViewModel() {
    private val api by di.instance<MainApi>()

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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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