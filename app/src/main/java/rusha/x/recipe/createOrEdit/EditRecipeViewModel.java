package rusha.x.recipe.createOrEdit;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.Disposable;
import rusha.x.BaseViewModel;
import rusha.x.MainApi;
import rusha.x.RxJavaSchedulers;
import rusha.x.SingleLiveEvent;

public class EditRecipeViewModel extends BaseViewModel {

    private MainApi api;
    private RxJavaSchedulers schedulers;

    public MutableLiveData<List<CreateOrEditRecipeIngredient>> ingredientsLiveData = new MutableLiveData<>(Collections.emptyList());
    public MutableLiveData<String> recipeNameLiveData = new MutableLiveData<>("");
    public MutableLiveData<String> recipeDescriptionLiveData = new MutableLiveData<>("");
    public SingleLiveEvent<Void> goBack = new SingleLiveEvent<>();

    @Inject
    public EditRecipeViewModel(MainApi api, RxJavaSchedulers schedulers) {
        super();
        this.api = api;
        this.schedulers = schedulers;

        List<CreateOrEditRecipeIngredient> initialIngredients = Arrays.asList(
                new CreateOrEditRecipeIngredient(
                        3.0,
                        new CreateOrEditProductByName(
                                null,
                                "trupDevstvenici",
                                5.0,
                                "u."
                        )
                ),
                new CreateOrEditRecipeIngredient(
                        5.0,
                        new CreateOrEditProductByName(
                                null,
                                "nozgik",
                                1.0,
                                "u."
                        )
                )
        );

        ingredientsLiveData.setValue(initialIngredients);
    }


    public void onIngredientPlusClick(CreateOrEditRecipeIngredient ingredient) {
        List<CreateOrEditRecipeIngredient> newIngredients = new ArrayList<>();
        List<CreateOrEditRecipeIngredient> oldIngredients = ingredientsLiveData.getValue();
        if (oldIngredients != null) {
            for (CreateOrEditRecipeIngredient currentIngredient : oldIngredients) {
                if (currentIngredient.equals(ingredient)) {
                    newIngredients.add(currentIngredient.withIncrementCount());
                } else {
                    newIngredients.add(currentIngredient);
                }
            }
        }
        ingredientsLiveData.setValue(newIngredients);
    }

    public void onIngredientMinusClick(CreateOrEditRecipeIngredient ingredient) {
        List<CreateOrEditRecipeIngredient> newIngredients = new ArrayList<>();
        List<CreateOrEditRecipeIngredient> oldIngredients = ingredientsLiveData.getValue();
        if (oldIngredients != null) {
            for (CreateOrEditRecipeIngredient currentIngredient : oldIngredients) {
                if (currentIngredient.equals(ingredient)) {
                    newIngredients.add(currentIngredient.withDecrementCount());
                } else {
                    newIngredients.add(currentIngredient);
                }
            }
        }
        ingredientsLiveData.setValue(newIngredients);
    }

    public void onSetIngredientCount(CreateOrEditRecipeIngredient ingredient, double count) {
        List<CreateOrEditRecipeIngredient> newIngredients = new ArrayList<>();
        List<CreateOrEditRecipeIngredient> oldIngredients = ingredientsLiveData.getValue();
        if (oldIngredients != null) {
            for (CreateOrEditRecipeIngredient currentIngredient : oldIngredients) {
                if (currentIngredient.equals(ingredient)) {
                    newIngredients.add(currentIngredient.withCount(count));
                } else {
                    newIngredients.add(currentIngredient);
                }
            }
        }
        ingredientsLiveData.setValue(newIngredients);
    }

    private String recipeName = "";

    public void onSetRecipeName(String name) {
        recipeName = name;
    }

    private String recipeDescription = "";

    public void onSetRecipeDescription(String description) {
        recipeDescription = description;
    }

    public void onSaveClick() {
        Disposable disposable = api.createOrEditRecipe(
                new CreateOrEditRecipe(
                        null,
                        recipeName,
                        recipeDescription,
                        ingredientsLiveData.getValue()
                )
        ).subscribeOn(
                schedulers.subscribe
        ).observeOn(
                schedulers.observe
        ).subscribe(() -> {
            goBack.call();
        });
        disposeOnExit(disposable);
    }
}