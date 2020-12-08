package rusha.x

import dagger.Component
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rusha.x.basket.BasketFragment
import rusha.x.product.ProductDetailsFragment
import rusha.x.recipe.createOrEdit.EditRecipeFragment
import rusha.x.recipe.details.RecipeDetailsFragment
import rusha.x.recipe.list.RecipeListFragment
import rusha.x.recipe.list.RecipeListViewModel
import javax.inject.Singleton

@Module
open class AppModule {

    @Provides
    open fun rxJavaSchedulers() = RxJavaSchedulers(
        subscribe = Schedulers.io(),
        observe = AndroidSchedulers.mainThread()
    )

    @Provides
    open fun mainApi(): MainApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.15:9999")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit.create(MainApi::class.java)
    }
}

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: EditRecipeFragment)
    fun inject(fragment: RecipeListFragment)
    fun inject(basketFragment: BasketFragment)
    fun inject(productDetailsFragment: ProductDetailsFragment)
    fun inject(recipeDetailsFragment: RecipeDetailsFragment)

    fun recipeListViewModel(): RecipeListViewModel
}