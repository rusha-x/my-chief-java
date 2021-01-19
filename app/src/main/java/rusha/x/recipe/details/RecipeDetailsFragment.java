package rusha.x.recipe.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import javax.inject.Inject;

import rusha.x.MainActivity;
import rusha.x.databinding.RecipeDetailsActivityBinding;
import rusha.x.recipe.Recipe;

public class RecipeDetailsFragment extends Fragment {
    @Inject
    public RecipeDetailsViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.di(this).inject(this);
        Recipe recipe = RecipeDetailsFragmentArgs.fromBundle(getArguments()).getRecipe();
        viewModel.init(recipe);
    }

    private RecipeDetailsActivityBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = RecipeDetailsActivityBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.recipeNameLiveData.observe(getViewLifecycleOwner(), recipeName -> {
            binding.nameView.setText(recipeName);
        });

        viewModel.ingredientsLiveData.observe(getViewLifecycleOwner(), ingredients -> {
            IngredientsListAdapter ingredientsViewAdapter = new IngredientsListAdapter(viewModel);
            ingredientsViewAdapter.setIngredientsToAdopt(ingredients);
            binding.ingredientsView.setAdapter(ingredientsViewAdapter);
        });

        viewModel.goToProductDetails.observe(getViewLifecycleOwner(), product -> {
            Navigation.findNavController(view).navigate(
                    RecipeDetailsFragmentDirections.actionRecipeDetailsFragmentToProductDetailsFragment(
                            product
                    )
            );
        });
    }
}