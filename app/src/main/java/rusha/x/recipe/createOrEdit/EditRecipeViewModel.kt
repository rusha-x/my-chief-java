package rusha.x.recipe.createOrEdit

import androidx.lifecycle.MutableLiveData
import org.kodein.di.instance
import rusha.x.*

class EditRecipeViewModel : BaseViewModel() {
    private val api by di.instance<MainApi>()
    private val schedulers by di.instance<RxJavaSchedulers>()

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
            schedulers.subscribe
        ).observeOn(
            schedulers.observe
        ).managedSubscribe { result ->
            goBack.call()
        }
    }
}