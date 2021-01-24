package rusha.x;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    public RxJavaSchedulers rxJavaSchedulers() {
        return new RxJavaSchedulers(Schedulers.io(), AndroidSchedulers.mainThread());
    }

    @Provides
    public MainApi mainApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-little-chief-server.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        return retrofit.create(MainApi.class);
    }
}