package rusha.x.recipe.createOrEdit;


import java.util.Objects;

public class CreateOrEditRecipeIngredient {
    public final double countInRecipe;
    public final CreateOrEditProductByName product;

    public CreateOrEditRecipeIngredient(double countInRecipe, CreateOrEditProductByName product) {
        this.countInRecipe = countInRecipe;
        this.product = product;
    }

    public CreateOrEditRecipeIngredient withDecrementCount() {
        double newCountInRecipe = Math.max(countInRecipe - 1, 0.0);
        return new CreateOrEditRecipeIngredient(newCountInRecipe, product);
    }

    public CreateOrEditRecipeIngredient withIncrementCount() {
        double newCountInRecipe = countInRecipe + 1;
        return new CreateOrEditRecipeIngredient(newCountInRecipe, product);
    }

    public final CreateOrEditRecipeIngredient withCount(double count) {
        return new CreateOrEditRecipeIngredient(count, product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrEditRecipeIngredient that = (CreateOrEditRecipeIngredient) o;
        return Double.compare(that.countInRecipe, countInRecipe) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countInRecipe, product);
    }
}