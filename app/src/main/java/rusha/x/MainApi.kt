package rusha.x

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.PUT
import rusha.x.recipe.createOrEdit.CreateOrEditRecipe
import rusha.x.recipe.Recipe

interface MainApi {

    @PUT("/recipe")
    fun createOrEditRecipe(body: CreateOrEditRecipe): Single<Unit>

    @GET("/recipe/all")
    fun allRecipe(): Single<List<Recipe>>
}


//val recipesJson = httpClient.get<String>(
//    "http://192.168.0.15:9999/recipe/all"
//)
//val allRecipes = json.parse(
//    deserializer = Recipe.serializer().list,
//    string = recipesJson
//)




//val createOrEditRecipe = CreateOrEditRecipe(
//    name = recipeName,
//    description = recipeDescription,
//    ingredients = viewProxy.ingredients
//)
//val createOrEditRecipeJson = json.stringify(
//    CreateOrEditRecipe.serializer(),
//    createOrEditRecipe
//)
//httpClient.put<Unit>("http://192.168.0.15:9999/recipe") {
//    contentType(ContentType.Application.Json)
//    body = createOrEditRecipeJson
//}



