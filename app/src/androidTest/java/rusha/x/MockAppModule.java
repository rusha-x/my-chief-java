package rusha.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import rusha.x.recipe.Recipe;
import rusha.x.recipe.createOrEdit.CreateOrEditRecipe;

@Module
public class MockAppModule extends AppModule {

    public static List<Recipe> expectedRecipes = Collections.singletonList(
            new Recipe(
                    1,
                    "Test",
                    "Test Test",
                    new ArrayList<>()
            )
    );

    @Provides
    @Override
    public RxJavaSchedulers rxJavaSchedulers() {
        return new RxJavaSchedulers(
                Schedulers.trampoline(),
                Schedulers.trampoline()
        );
    }

    @Provides
    @Override
    public MainApi mainApi() {
        return new MainApi() {
            @Override
            public Single<Void> createOrEditRecipe(CreateOrEditRecipe body) {
                throw new AssertionError();
            }

            @Override
            public Single<List<Recipe>> allRecipes() {
                return Single.just(expectedRecipes);
            }
        };
    }
}