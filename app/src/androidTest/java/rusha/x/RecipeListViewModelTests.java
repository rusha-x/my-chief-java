package rusha.x;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import rusha.x.recipe.Recipe;
import rusha.x.recipe.list.RecipeListViewModel;

@RunWith(AndroidJUnit4.class)
public class RecipeListViewModelTests {

    @UiThreadTest
    @Test
    public void loadRecipesOnInit() {
        RecipeListViewModel viewModel = di().recipeListViewModel();
        viewModel.onResume();

        Assert.assertEquals(viewModel.recipesLiveData.getValue(), MockAppModule.expectedRecipes);
    }

    @UiThreadTest
    @Test
    public void loadRecipesOnRefresh() {
        RecipeListViewModel viewModel = di().recipeListViewModel();
        viewModel.onRefresh();

        Assert.assertEquals(viewModel.recipesLiveData.getValue(), MockAppModule.expectedRecipes);
    }

    @UiThreadTest
    @Test
    public void loadRecipesOnClick() {

        RecipeListViewModel viewModel = di().recipeListViewModel();
        boolean[] wasToEditRecipeCalled = {false};
        viewModel.goToEditRecipe.observe(mockLifecycleOwner(), (params) -> {
            wasToEditRecipeCalled[0] = true;
        });

        viewModel.onAddRecipeClick();

        Assert.assertTrue(wasToEditRecipeCalled[0]);
    }

    @UiThreadTest
    @Test
    public void loadAddRecipesOnClick() {

        Recipe expectedRecipe = new Recipe(
                4,
                "Shpack",
                "Shpack Shpack",
                new ArrayList<>()
        );

        RecipeListViewModel viewModel = di().recipeListViewModel();
        viewModel.onRecipeClick(expectedRecipe);

        Assert.assertEquals(viewModel.goToRecipeDetails.getValue(), expectedRecipe);
    }

    private LifecycleOwner mockLifecycleOwner() {
        LifecycleOwner owner = Mockito.mock(LifecycleOwner.class);
        LifecycleRegistry lifecycle = new LifecycleRegistry(owner);
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        Mockito.when(owner.getLifecycle()).thenReturn(lifecycle);
        return owner;
    }

    private AppComponent di() {
        return DaggerAppComponent.builder().appModule(new MockAppModule()).build();
    }
}