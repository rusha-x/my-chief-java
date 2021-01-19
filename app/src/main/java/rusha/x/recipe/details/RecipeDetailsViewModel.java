package rusha.x.recipe.details;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import rusha.x.BaseViewModel;
import rusha.x.SingleLiveEvent;
import rusha.x.product.Product;
import rusha.x.recipe.Recipe;
import rusha.x.recipe.RecipeIngredient;

public class RecipeDetailsViewModel extends BaseViewModel {
    @Inject
    public RecipeDetailsViewModel() {
        super();
    }

    public MutableLiveData<String> recipeNameLiveData = new MutableLiveData<>("");
    public MutableLiveData<List<RecipeIngredient>> ingredientsLiveData = new MutableLiveData<>();
    public SingleLiveEvent<Product> goToProductDetails = new SingleLiveEvent<>();

    public void init(Recipe recipe) {
        recipeNameLiveData.setValue(recipe.name);
        ingredientsLiveData.setValue(recipe.ingredients);
    }

    public void onIngredientClick(RecipeIngredient ingredient) {
        goToProductDetails.setValue(ingredient.product);
    }
}