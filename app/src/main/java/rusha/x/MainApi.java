package rusha.x;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import rusha.x.recipe.Recipe;
import rusha.x.recipe.createOrEdit.CreateOrEditRecipe;

public interface MainApi {

    @PUT("/recipe")
    Completable createOrEditRecipe(@Body CreateOrEditRecipe body);

    @GET("/recipe/all")
    Single<List<Recipe>> allRecipes();
}