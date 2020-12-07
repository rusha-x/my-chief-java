package rusha.x.recipe.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.recipe_details_activity.*
import rusha.x.R

class RecipeDetailsFragment : Fragment(R.layout.recipe_details_activity) {
    private lateinit var viewModel: RecipeDetailsViewModel

    private val args by navArgs<RecipeDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeDetailsViewModel::class.java)
        viewModel.init(recipe = args.recipe)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recipeNameLiveData.observe(viewLifecycleOwner, Observer { recipeName ->
            nameView.text = recipeName
        })

        viewModel.ingredientsLiveData.observe(viewLifecycleOwner, Observer { ingredients ->
            val ingredientsViewAdapter = IngredientsListAdapter(viewModel)
            ingredientsViewAdapter.ingredientsToAdopt = ingredients
            ingredientsView.adapter = ingredientsViewAdapter
        })

        viewModel.goToProductDetails.observe(viewLifecycleOwner, Observer { product ->
            findNavController().navigate(
                RecipeDetailsFragmentDirections.actionRecipeDetailsFragmentToProductDetailsFragment(
                    product
                )
            )
        })
    }
}