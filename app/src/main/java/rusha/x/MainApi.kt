package rusha.x

import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import rusha.x.recipe.Recipe
import rusha.x.recipe.createOrEdit.CreateOrEditRecipe

interface MainApi {

    @PUT("/recipe")
    fun createOrEditRecipe(@Body body: CreateOrEditRecipe): Single<Unit>

    @GET("/recipe/all")
    fun allRecipe(): Single<List<Recipe>>
}