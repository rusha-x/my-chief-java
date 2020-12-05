package rusha.x

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val di = DI {

    bind<MainApi>() with singleton {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.15:9999")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(MainApi::class.java)
    }
}