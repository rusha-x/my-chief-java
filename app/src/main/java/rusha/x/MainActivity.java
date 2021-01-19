package rusha.x;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import rusha.x.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {
    public AppComponent di;
    public MainActivityBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        binding = MainActivityBinding.inflate(inflater);
        setContentView(binding.getRoot());
        di = DaggerAppComponent.create();

        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
    }

    public static AppComponent di(Fragment fragment) {
        return ((MainActivity) fragment.requireActivity()).di;
    }
}