package rusha.x.recipe.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import javax.inject.Inject;

import rusha.x.MainActivity;
import rusha.x.databinding.RecipeListActivityBinding;

public class RecipeListFragment extends Fragment {
    @Inject
    public RecipeListViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.di(this).inject(this);
    }

    private RecipeListActivityBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = RecipeListActivityBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addRecipeButton.setOnClickListener(v -> {
            viewModel.onAddRecipeClick();
        });

        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.onRefresh();
        });

        viewModel.recipesLiveData.observe(getViewLifecycleOwner(), (recipes) -> {
            RecipesListAdapter recipesViewAdapter = new RecipesListAdapter(viewModel);
            recipesViewAdapter.setRecipesToAdopt(recipes);
            binding.recipesView.setAdapter(recipesViewAdapter);
        });

        viewModel.isRefreshingLiveData.observe(getViewLifecycleOwner(), (isRefreshing) -> {
            binding.swipeRefreshLayout.setRefreshing(isRefreshing);
        });
        viewModel.goToEditRecipe.observe(getViewLifecycleOwner(), (goToEditRecipe) -> {
            Navigation.findNavController(view).navigate(
                    RecipeListFragmentDirections.actionRecipeListFragmentToEditRecipeFragment()
            );
        });
        viewModel.goToRecipeDetails.observe(getViewLifecycleOwner(), (recipe) -> {
            Navigation.findNavController(view).navigate(
                    RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailsFragment(recipe)
            );
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }
}
