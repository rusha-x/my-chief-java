package rusha.x

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_activity.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        di = DI {
            bind<MainApi>() with singleton {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.0.15:9999")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
                retrofit.create(MainApi::class.java)
            }
            bind<RxJavaSchedulers>() with singleton {
                RxJavaSchedulers(
                    subscribe = Schedulers.io(),
                    observe = AndroidSchedulers.mainThread()
                )
            }
        }

        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.findNavController())
    }
}