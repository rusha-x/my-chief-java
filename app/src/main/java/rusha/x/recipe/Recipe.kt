package rusha.x.recipe

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import rusha.x.product.Product

@Parcelize
data class Recipe(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("ingredients") val ingredients: List<Ingredient>
) : Parcelable {

    @Parcelize
    data class Ingredient(
        @SerializedName("countInRecipe")val countInRecipe: Double,
        @SerializedName("product")val product: Product
    ) : Parcelable
}