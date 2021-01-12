package rusha.x.recipe.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import rusha.x.R
import rusha.x.databinding.RecipeListActivityBinding
import rusha.x.di
import rusha.x.recipe.Recipe
import javax.inject.Inject

class RecipeListFragment : Fragment() {
    @Inject
    lateinit var viewModel: RecipeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        di().inject(this)
    }

    private lateinit var binding: RecipeListActivityBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RecipeListActivityBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addRecipeButton.setOnClickListener {
            viewModel.onAddRecipeClick()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        viewModel.recipesLiveData.observe(viewLifecycleOwner, Observer { recipes ->
            val recipesViewAdapter = RecipesListAdapter(viewModel)
            recipesViewAdapter.recipesToAdopt = recipes
            binding.recipesView.adapter = recipesViewAdapter
        })

        viewModel.isRefreshingLiveData.observe(viewLifecycleOwner, Observer { isRefreshing ->
            binding.swipeRefreshLayout.isRefreshing = isRefreshing
        })
        viewModel.goToEditRecipe.observe(viewLifecycleOwner, Observer { goToEditRecipe ->
            findNavController().navigate(
                RecipeListFragmentDirections.actionRecipeListFragmentToEditRecipeFragment()
            )
        })
        viewModel.goToRecipeDetails.observe(viewLifecycleOwner, Observer { recipe: Recipe ->
            findNavController().navigate(
                RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailsFragment(recipe)
            )
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}
