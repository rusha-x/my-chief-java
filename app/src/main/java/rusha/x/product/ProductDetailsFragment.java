package rusha.x.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import rusha.x.MainActivity;
import rusha.x.R;
import rusha.x.databinding.ProductDetailsFragmentBinding;

public class ProductDetailsFragment extends Fragment {
    @Inject
    public ProductDetailsViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.di(this).inject(this);
        Product product = ProductDetailsFragmentArgs.fromBundle(getArguments()).getProduct();
        viewModel.init(product);
    }

    private ProductDetailsFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = ProductDetailsFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.nameLiveData.observe(getViewLifecycleOwner(), name -> {
            binding.nameView.setText(name);
        });

        viewModel.formattedPriceLiveData.observe(getViewLifecycleOwner(), formattedPrice -> {
            binding.priceView.setText(formattedPrice);
        });

        viewModel.showWelcomeLiveData.observe(getViewLifecycleOwner(), params -> {
            if (params != null) {
                Toast.makeText(requireContext(), params.text, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }
}