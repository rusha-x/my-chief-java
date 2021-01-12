package rusha.x.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.recipe_details_activity.view.*
import rusha.x.R
import rusha.x.databinding.ProductDetailsFragmentBinding
import rusha.x.di
import javax.inject.Inject

class ProductDetailsFragment : Fragment() {
    @Inject
    lateinit var viewModel: ProductDetailsViewModel

    private val args by navArgs<ProductDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        di().inject(this)
        viewModel.init(args.product)
    }

    private lateinit var binding: ProductDetailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductDetailsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.nameLiveData.observe(viewLifecycleOwner, Observer { name ->
            binding.nameView.text = name
        })

        viewModel.formattedPriceLiveData.observe(viewLifecycleOwner, Observer { formattedPrice ->
            binding.priceView.text = formattedPrice
        })

        viewModel.showWelcomeLiveData.observe(viewLifecycleOwner, Observer { params ->
            if (params != null) {
                Toast.makeText(requireContext(), params.text, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}