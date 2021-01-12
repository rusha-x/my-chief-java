package rusha.x.recipe.createOrEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import rusha.x.R
import rusha.x.databinding.EditRecipeFragmentBinding
import rusha.x.di
import javax.inject.Inject

class EditRecipeFragment : Fragment() {

    @Inject
    lateinit var viewModel: EditRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        di().inject(this)
    }

    private lateinit var binding: EditRecipeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditRecipeFragmentBinding.inflate(inflater)
        return binding.root
    }

    private lateinit var ingredientsViewAdapter: EditRecipeIngredientsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.ingredientsLiveData.observe(viewLifecycleOwner, Observer { ingredients ->
            ingredientsViewAdapter.ingredientsToAdopt = ingredients
            ingredientsViewAdapter.notifyDataSetChanged()
        })

        viewModel.recipeNameLiveData.observe(viewLifecycleOwner, Observer { recipeName ->
            binding.nameRecipeEdit.setText(recipeName)
        })

        viewModel.recipeDescriptionLiveData.observe(viewLifecycleOwner, Observer { recipeDescription ->
            binding.descriptionRecipeEdit.setText(recipeDescription)
        })

        viewModel.goBack.observe(viewLifecycleOwner, Observer {
            findNavController().navigateUp()
        })

        binding.save.setOnClickListener {
            viewModel.onSaveClick()
        }

        binding.nameRecipeEdit.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val name = binding.nameRecipeEdit.text.toString()
                viewModel.onSetRecipeName(name)
            }
        }

        binding.descriptionRecipeEdit.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val description = binding.descriptionRecipeEdit.text.toString()
                viewModel.onSetRecipeDescription(description)
            }
        }

        ingredientsViewAdapter = EditRecipeIngredientsAdapter(viewModel)
        binding.editRecipeIngredientsView.adapter = ingredientsViewAdapter
    }
}