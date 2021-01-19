package rusha.x.recipe.createOrEdit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import javax.inject.Inject;

import rusha.x.MainActivity;
import rusha.x.databinding.EditRecipeFragmentBinding;

public class EditRecipeFragment extends Fragment {

    @Inject
    public EditRecipeViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.di(this).inject(this);
    }

    private EditRecipeFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = EditRecipeFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    public EditRecipeIngredientsAdapter ingredientsViewAdapter;

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.ingredientsLiveData.observe(getViewLifecycleOwner(), ingredients -> {
            ingredientsViewAdapter.setIngredientsToAdopt(ingredients);
            ingredientsViewAdapter.notifyDataSetChanged();
        });

        viewModel.recipeNameLiveData.observe(getViewLifecycleOwner(), recipeName -> {
            binding.nameRecipeEdit.setText(recipeName);
        });

        viewModel.recipeDescriptionLiveData.observe(getViewLifecycleOwner(), recipeDescription -> {
            binding.descriptionRecipeEdit.setText(recipeDescription);
        });

        viewModel.goBack.observe(getViewLifecycleOwner(), Observer -> {
            Navigation.findNavController(view).navigateUp();
        });

        binding.save.setOnClickListener(v -> viewModel.onSaveClick());

        binding.nameRecipeEdit.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String name = binding.nameRecipeEdit.getText().toString();
                viewModel.onSetRecipeName(name);
            }
        });

        binding.descriptionRecipeEdit.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String description = binding.descriptionRecipeEdit.getText().toString();
                viewModel.onSetRecipeDescription(description);
            }
        });

        ingredientsViewAdapter = new EditRecipeIngredientsAdapter(viewModel);
        binding.editRecipeIngredientsView.setAdapter(ingredientsViewAdapter);
    }
}