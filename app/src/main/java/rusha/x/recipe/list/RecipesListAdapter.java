package rusha.x.recipe.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import rusha.x.databinding.RecipeListItemBinding;
import rusha.x.recipe.Recipe;

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.ViewHolder> {

    private RecipeListViewModel viewModel;

    public RecipesListAdapter(RecipeListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private List<Recipe> recipesToAdopt = Collections.emptyList();

    public void setRecipesToAdopt(List<Recipe> recipes) {
        recipesToAdopt = recipes;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecipeListItemBinding binding = RecipeListItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return recipesToAdopt.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipeOnPosition = recipesToAdopt.get(position);
        holder.bind(recipeOnPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RecipeListItemBinding binding;

        public ViewHolder(RecipeListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Recipe recipe) {
            binding.recipesItem.setText(recipe.name);
            binding.getRoot().setOnClickListener(v -> {
                viewModel.onRecipeClick(recipe);
            });
        }
    }
}