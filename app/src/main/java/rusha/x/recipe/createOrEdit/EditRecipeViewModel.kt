package rusha.x.recipe.createOrEdit

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.kodein.di.instance
import rusha.x.BaseViewModel
import rusha.x.MainApi
import rusha.x.SingleLiveEvent
import rusha.x.di

class EditRecipeViewModel : BaseViewModel() {
    private val api by di.instance<MainApi>()

    val ingredientsLiveData = MutableLiveData<List<CreateOrEditRecipe.Ingredient>>(emptyList())
    val recipeNameLiveData = MutableLiveData<String>("")
    val recipeDescriptionLiveData = MutableLiveData<String>("")
    val goBack = SingleLiveEvent<Nothing>()

    init {
        val initialIngredients: List<CreateOrEditRecipe.Ingredient> = listOf(
            CreateOrEditRecipe.Ingredient(
                countInRecipe = 3.0,
                product = CreateOrEditProductByName(
                    name = "trupDevstvenici",
                    price = 5.0,
                    unit = "u."
                )
            ),
            CreateOrEditRecipe.Ingredient(
                countInRecipe = 5.0,
                product = CreateOrEditProductByName(
                    name = "nozgik",
                    price = 1.0,
                    unit = "u."
                )
            )
        )

        ingredientsLiveData.value = initialIngredients
    }

    fun onIngredientPlusClick(ingredient: CreateOrEditRecipe.Ingredient) {
        ingredientsLiveData.value = ingredientsLiveData.value?.map { currentIngredient ->
            if (currentIngredient == ingredient)
                currentIngredient.withIncrementCount()
            else
                currentIngredient
        }
    }

    fun onIngredientMinusClick(ingredient: CreateOrEditRecipe.Ingredient) {
        ingredientsLiveData.value = ingredientsLiveData.value?.map { currentIngredient ->
            if (currentIngredient == ingredient)
                currentIngredient.withDecrementCount()
            else
                currentIngredient
        }
    }

    fun onSetIngredientCount(ingredient: CreateOrEditRecipe.Ingredient, count: Double) {
        ingredientsLiveData.value = ingredientsLiveData.value?.map { currentIngredient ->
            if (currentIngredient == ingredient)
                currentIngredient.withCount(count)
            else
                currentIngredient
        }
    }

    private var recipeName: String = ""
    fun onSetRecipeName(name: String) {
        recipeName = name
    }

    private var recipeDescription: String = ""
    fun onSetRecipeDescription(description: String) {
        recipeDescription = description
    }

    fun onSaveClick() {
        api.createOrEditRecipe(
            body = CreateOrEditRecipe(
                name = recipeName,
                description = recipeDescription,
                ingredients = ingredientsLiveData.value ?: emptyList()
            )
        ).subscribeOn(
            Schedulers.io()
        ).observeOn(
            AndroidSchedulers.mainThread()
        ).managedSubscribe { result ->
            goBack.call()
        }
    }
}