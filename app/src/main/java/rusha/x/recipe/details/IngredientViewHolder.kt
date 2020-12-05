package rusha.x.recipe.details

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_details_item.view.*
import rusha.x.recipe.Recipe

/**
 * Держатель отображения ячейки
 */
class IngredientViewHolder(
    val viewModel: RecipeDetailsViewModel,
    /**
     * Отображение ячейки (ConstraintLayout из recipe_details_item.xml)
     */
    val cellView: View
) : RecyclerView.ViewHolder(cellView) {

    /**
     * Держатель ячейки заполняет отображение ячейки данными
     */
    fun bind(ingredient: Recipe.Ingredient) {
        cellView.setOnClickListener {
            viewModel.onIngredientClick(ingredient)
        }
        cellView.nameItemView.text = ingredient.product.name
        cellView.countView.text = ingredient.countInRecipe.toString()
    }
}