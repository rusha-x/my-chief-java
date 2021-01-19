package rusha.x;

import javax.inject.Singleton;

import dagger.Component;
import rusha.x.basket.BasketFragment;
import rusha.x.product.ProductDetailsFragment;
import rusha.x.recipe.createOrEdit.EditRecipeFragment;
import rusha.x.recipe.details.RecipeDetailsFragment;
import rusha.x.recipe.list.RecipeListFragment;
import rusha.x.recipe.list.RecipeListViewModel;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    void inject(EditRecipeFragment fragment);

    void inject(RecipeListFragment fragment);

    void inject(BasketFragment fragment);

    void inject(ProductDetailsFragment fragment);

    void inject(RecipeDetailsFragment fragment);

    RecipeListViewModel recipeListViewModel();
}