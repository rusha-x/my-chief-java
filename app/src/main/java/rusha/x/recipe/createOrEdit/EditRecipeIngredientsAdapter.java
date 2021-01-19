package rusha.x.recipe.createOrEdit;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import rusha.x.databinding.EditRecipeIngredientBinding;

public class EditRecipeIngredientsAdapter
        extends RecyclerView.Adapter<EditRecipeIngredientsAdapter.IngredientViewHolder> {

    private EditRecipeViewModel viewModel;

    public EditRecipeIngredientsAdapter(EditRecipeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private List<CreateOrEditRecipeIngredient> ingredientsToAdopt = Collections.emptyList();

    public void setIngredientsToAdopt(List<CreateOrEditRecipeIngredient> ingredients) {
        ingredientsToAdopt = ingredients;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ingredientsToAdopt.size();
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EditRecipeIngredientBinding binding = EditRecipeIngredientBinding.inflate(inflater, parent, false);
        return new IngredientViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        CreateOrEditRecipeIngredient ingredientOnPosition = ingredientsToAdopt.get(position);
        holder.bind(ingredientOnPosition);
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        private EditRecipeIngredientBinding binding;

        public IngredientViewHolder(EditRecipeIngredientBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CreateOrEditRecipeIngredient ingredient) {
            binding.addIngredient.setText(ingredient.product.name);
            binding.ingredientCount.setText(String.valueOf(ingredient.countInRecipe));

            binding.plus.setOnClickListener((v) -> {
                viewModel.onIngredientPlusClick(ingredient);
            });

            binding.minus.setOnClickListener((v) -> {
                viewModel.onIngredientMinusClick(ingredient);
            });

            binding.ingredientCount.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    double count = Double.parseDouble(binding.ingredientCount.getText().toString());
                    viewModel.onSetIngredientCount(ingredient, count);
                }
            });
        }
    }
}