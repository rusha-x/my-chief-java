package rusha.x.recipe.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import rusha.x.R
import rusha.x.databinding.RecipeDetailsActivityBinding
import rusha.x.di
import javax.inject.Inject

class RecipeDetailsFragment : Fragment() {
    @Inject
    lateinit var viewModel: RecipeDetailsViewModel

    private val args by navArgs<RecipeDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        di().inject(this)
        viewModel.init(args.recipe)
    }

    private lateinit var binding: RecipeDetailsActivityBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RecipeDetailsActivityBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recipeNameLiveData.observe(viewLifecycleOwner, Observer { recipeName ->
            binding.nameView.text = recipeName
        })

        viewModel.ingredientsLiveData.observe(viewLifecycleOwner, Observer { ingredients ->
            val ingredientsViewAdapter = IngredientsListAdapter(viewModel)
            ingredientsViewAdapter.ingredientsToAdopt = ingredients
            binding.ingredientsView.adapter = ingredientsViewAdapter
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