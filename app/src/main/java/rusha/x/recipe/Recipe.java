package rusha.x.recipe;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe implements Parcelable {
    @SerializedName("id")
    public final int id;

    @SerializedName("name")
    public final String name;

    @SerializedName("description")
    public final String description;

    @SerializedName("ingredients")
    public final List<RecipeIngredient> ingredients;

    public Recipe(int id, String name, String description, List<RecipeIngredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        ingredients = in.createTypedArrayList(RecipeIngredient.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeTypedList(ingredients);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}