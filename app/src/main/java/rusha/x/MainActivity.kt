package rusha.x

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    lateinit var di: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        di = DaggerAppComponent.create()
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.findNavController())
    }
}

fun Fragment.di(): AppComponent {
    return (this.requireActivity() as MainActivity).di
}