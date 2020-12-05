package rusha.x.product

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.product_details_fragment.*
import rusha.x.R

class ProductDetailsFragment : Fragment(R.layout.product_details_fragment) {
    private lateinit var viewModel: ProductDetailsViewModel

    private val args by navArgs<ProductDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        viewModel.init(product = args.product)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.nameLiveData.observe(viewLifecycleOwner, Observer { name ->
            nameView.text = name
        })

        viewModel.formattedPriceLiveData.observe(viewLifecycleOwner, Observer { formattedPrice ->
            priceView.text = formattedPrice
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