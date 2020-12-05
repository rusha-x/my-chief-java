package rusha.x.basket

import com.google.gson.annotations.SerializedName
import rusha.x.product.Product

data class Basket(
    @SerializedName("id") val id: String,
    @SerializedName("items") val items: List<Item>
) {
    data class Item(
        @SerializedName("count") val count: Double,
        @SerializedName("product") val product: Product
    )
}