package rusha.x.recipe.createOrEdit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateOrEditRecipe {
    @SerializedName("id")
    public final Integer id;

    @SerializedName("name")
    public final String name;

    @SerializedName("description")
    public final String description;

    @SerializedName("ingredients")
    public final List<CreateOrEditRecipeIngredient> ingredients;

    public CreateOrEditRecipe(Integer id, String name, String description, List<CreateOrEditRecipeIngredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }
}