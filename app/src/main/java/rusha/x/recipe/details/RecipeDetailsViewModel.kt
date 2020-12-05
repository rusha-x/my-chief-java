package rusha.x.recipe.details

import androidx.lifecycle.MutableLiveData
import rusha.x.BaseViewModel
import rusha.x.SingleLiveEvent
import rusha.x.product.Product
import rusha.x.recipe.Recipe

class RecipeDetailsViewModel : BaseViewModel() {
    val recipeNameLiveData = MutableLiveData<String>("")
    val ingredientsLiveData = MutableLiveData<List<Recipe.Ingredient>>()
    val goToProductDetails = SingleLiveEvent<Product>()

    fun init(recipe: Recipe) {
        recipeNameLiveData.value = recipe.name
        ingredientsLiveData.value = recipe.ingredients
    }

    fun onIngredientClick(ingredient: Recipe.Ingredient) {
        goToProductDetails.value = ingredient.product
    }
}