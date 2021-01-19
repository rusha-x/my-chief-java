package rusha.x.basket;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Basket {
    @SerializedName("id")
    public final String id;

    @SerializedName("items")
    public final List<BasketItem> items;

    public Basket(String id, List<BasketItem> items) {
        this.id = id;
        this.items = items;
    }
}