package rusha.x.recipe.details;

import androidx.recyclerview.widget.RecyclerView;

import rusha.x.databinding.RecipeDetailsItemBinding;
import rusha.x.recipe.RecipeIngredient;

/**
 * Держатель отображения ячейки
 */
public class IngredientViewHolder extends RecyclerView.ViewHolder {

    private RecipeDetailsViewModel viewModel;
    private RecipeDetailsItemBinding binding;

    public IngredientViewHolder(RecipeDetailsViewModel viewModel, RecipeDetailsItemBinding binding) {
        super(binding.getRoot());
        this.viewModel = viewModel;
        this.binding = binding;
    }

    /**
     * Держатель ячейки заполняет отображение ячейки данными
     */
    public void bind(RecipeIngredient ingredient) {
        binding.getRoot().setOnClickListener((v) -> {
            viewModel.onIngredientClick(ingredient);
        });
        binding.nameItemView.setText(ingredient.product.name);
        binding.countView.setText(String.valueOf(ingredient.countInRecipe));
    }
}