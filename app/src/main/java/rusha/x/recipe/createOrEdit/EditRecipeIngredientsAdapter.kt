package rusha.x.recipe.createOrEdit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.edit_recipe_ingredient.view.*
import rusha.x.R

class EditRecipeIngredientsAdapter(
    private val presenter: EditRecipeViewModel
) : RecyclerView.Adapter<EditRecipeIngredientsAdapter.IngredientViewHolder>() {

    var ingredientsToAdopt: List<CreateOrEditRecipe.Ingredient> = emptyList()
    override fun getItemCount(): Int {
        return ingredientsToAdopt.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.edit_recipe_ingredient,
            parent,
            false
        )
        return IngredientViewHolder(containerView = view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredientOnPosition = ingredientsToAdopt.get(index = position)
        holder.bind(ingredient = ingredientOnPosition)
    }

    inner class IngredientViewHolder(
        val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        fun bind(ingredient: CreateOrEditRecipe.Ingredient) {
            containerView.addIngredient.setText(ingredient.product.name)
            containerView.ingredientCount.setText(ingredient.countInRecipe.toString())

            containerView.plus.setOnClickListener {
                presenter.onIngredientPlusClick(ingredient)
            }

            containerView.minus.setOnClickListener {
                presenter.onIngredientMinusClick(ingredient)
            }

            containerView.ingredientCount.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    val count = containerView.ingredientCount.text.toString().toDouble()
                    presenter.onSetIngredientCount(ingredient, count)
                }
            }
        }
    }
}