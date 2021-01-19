package rusha.x.basket;

import com.google.gson.annotations.SerializedName;

import rusha.x.product.Product;

public class BasketItem {
    @SerializedName("count")
    public final double count;

    @SerializedName("product")
    public final Product product;

    public BasketItem(double count, Product product) {
        this.count = count;
        this.product = product;
    }
}