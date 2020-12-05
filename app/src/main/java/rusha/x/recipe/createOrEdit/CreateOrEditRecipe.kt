package rusha.x.recipe.createOrEdit

import com.google.gson.annotations.SerializedName


data class CreateOrEditRecipe(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("ingredients") val ingredients: List<Ingredient>
) {
    data class Ingredient(
        val countInRecipe: Double,
        val product: CreateOrEditProductByName
    ) {
        fun withIncrementCount(): Ingredient {
            return copy(
                countInRecipe = countInRecipe + 1
            )
        }

        fun withDecrementCount(): Ingredient {
            return copy(
                countInRecipe = (countInRecipe - 1).coerceAtLeast(minimumValue = 0.0)
            )
        }

        fun withCount(count: Double): Ingredient {
            return copy(
                countInRecipe = count
            )
        }
    }
}


data class CreateOrEditProductByName(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double,
    @SerializedName("unit") val unit: String
)