package rusha.x.recipe.list;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.Disposable;
import rusha.x.BaseViewModel;
import rusha.x.MainApi;
import rusha.x.RxJavaSchedulers;
import rusha.x.SingleLiveEvent;
import rusha.x.recipe.Recipe;

import static java.util.Collections.emptyList;

public class RecipeListViewModel extends BaseViewModel {

    private MainApi api;
    private RxJavaSchedulers schedulers;

    public MutableLiveData<List<Recipe>> recipesLiveData = new MutableLiveData<>(emptyList());
    public MutableLiveData<Boolean> isRefreshingLiveData = new MutableLiveData<>();
    public SingleLiveEvent<Void> goToEditRecipe = new SingleLiveEvent<>();
    public SingleLiveEvent<Recipe> goToRecipeDetails = new SingleLiveEvent<>();

    @Inject
    public RecipeListViewModel(MainApi api, RxJavaSchedulers schedulers) {
        super();
        this.api = api;
        this.schedulers = schedulers;
    }

    public void onRefresh() {
        updateRecipes();
    }

    public void onResume() {
        updateRecipes();
    }

    private void updateRecipes() {
        isRefreshingLiveData.setValue(true);
        Disposable disposable = api.allRecipes()
                .subscribeOn(schedulers.subscribe)
                .observeOn(schedulers.observe)
                .subscribe((allRecipes) -> {
                    recipesLiveData.setValue(allRecipes);
                    isRefreshingLiveData.setValue(false);
                });
        disposeOnExit(disposable);
    }

    public void onAddRecipeClick() {
        goToEditRecipe.call();
    }

    public void onRecipeClick(Recipe recipe) {
        goToRecipeDetails.setValue(recipe);
    }
}