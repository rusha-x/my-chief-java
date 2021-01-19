package rusha.x.recipe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import rusha.x.product.Product;

public class RecipeIngredient implements Parcelable {
    @SerializedName("countInRecipe")
    public final double countInRecipe;

    @SerializedName("product")
    public final Product product;

    public RecipeIngredient(double countInRecipe, Product product) {
        this.countInRecipe = countInRecipe;
        this.product = product;
    }

    protected RecipeIngredient(Parcel in) {
        countInRecipe = in.readDouble();
        product = in.readParcelable(Product.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(countInRecipe);
        dest.writeParcelable(product, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecipeIngredient> CREATOR = new Creator<RecipeIngredient>() {
        @Override
        public RecipeIngredient createFromParcel(Parcel in) {
            return new RecipeIngredient(in);
        }

        @Override
        public RecipeIngredient[] newArray(int size) {
            return new RecipeIngredient[size];
        }
    };
}