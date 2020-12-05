package rusha.x.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("id")val id: Int,
    @SerializedName("name")val name: String,
    @SerializedName("price")val price: Double,
    @SerializedName("unit")val unit: String
) : Parcelable