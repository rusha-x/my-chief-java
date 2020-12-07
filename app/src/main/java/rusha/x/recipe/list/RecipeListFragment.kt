package rusha.x.recipe.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_list_activity.*
import kotlinx.android.synthetic.main.recipe_list_item.view.*
import rusha.x.R
import rusha.x.recipe.Recipe

class RecipeListFragment : Fragment(R.layout.recipe_list_activity) {
    private lateinit var viewModel: RecipeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addRecipeButton.setOnClickListener {
            viewModel.onAddRecipeClick()
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        viewModel.recipesLiveData.observe(viewLifecycleOwner, Observer { recipes ->
            val recipesViewAdapter = RecipesListAdapter(viewModel)
            recipesViewAdapter.recipesToAdopt = recipes
            recipesView.adapter = recipesViewAdapter
        })

        viewModel.isRefreshingLiveData.observe(viewLifecycleOwner, Observer { isRefreshing ->
            swipeRefreshLayout.isRefreshing = isRefreshing
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


class RecipesListAdapter(
    private val viewModel: RecipeListViewModel
) : RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {
    var recipesToAdopt: List<Recipe> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.recipe_list_item,
            parent,
            false
        )
        return ViewHolder(cellView = view)
    }

    override fun getItemCount(): Int {
        return recipesToAdopt.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeOnPosition = recipesToAdopt.get(index = position)
        holder.bind(recipe = recipeOnPosition)
    }

    inner class ViewHolder(
        val cellView: View
    ) : RecyclerView.ViewHolder(cellView) {

        fun bind(recipe: Recipe) {
            cellView.recipesItem.text = recipe.name
            cellView.setOnClickListener {
                viewModel.onRecipeClick(recipe)
            }
        }
    }
}
