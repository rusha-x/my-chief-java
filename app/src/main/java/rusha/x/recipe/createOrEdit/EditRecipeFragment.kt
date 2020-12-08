package rusha.x.recipe.createOrEdit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.edit_recipe_fragment.*
import rusha.x.MainActivity
import rusha.x.R
import rusha.x.di
import javax.inject.Inject

class EditRecipeFragment : Fragment(R.layout.edit_recipe_fragment) {

    @Inject
    lateinit var viewModel: EditRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        di().inject(this)
    }

    private lateinit var ingredientsViewAdapter: EditRecipeIngredientsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.ingredientsLiveData.observe(viewLifecycleOwner, Observer { ingredients ->
            ingredientsViewAdapter.ingredientsToAdopt = ingredients
            ingredientsViewAdapter.notifyDataSetChanged()
        })

        viewModel.recipeNameLiveData.observe(viewLifecycleOwner, Observer { recipeName ->
            nameRecipeEdit.setText(recipeName)
        })

        viewModel.recipeDescriptionLiveData.observe(viewLifecycleOwner, Observer { recipeDescription ->
            descriptionRecipeEdit.setText(recipeDescription)
        })

        viewModel.goBack.observe(viewLifecycleOwner, Observer {
            findNavController().navigateUp()
        })

        save.setOnClickListener {
            viewModel.onSaveClick()
        }

        nameRecipeEdit.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val name = nameRecipeEdit.text.toString()
                viewModel.onSetRecipeName(name)
            }
        }

        descriptionRecipeEdit.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val description = descriptionRecipeEdit.text.toString()
                viewModel.onSetRecipeDescription(description)
            }
        }

        ingredientsViewAdapter = EditRecipeIngredientsAdapter(viewModel)
        editRecipeIngredientsView.adapter = ingredientsViewAdapter
    }
}