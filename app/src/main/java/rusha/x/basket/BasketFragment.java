package rusha.x.basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import javax.inject.Inject;

import rusha.x.MainActivity;
import rusha.x.databinding.BasketActivityBinding;

// Класс BasketActivity наследует все свойства Fragment т.е все его переменные и функции
public class BasketFragment extends Fragment {

    @Inject
    public BasketViewModel viewModel;

    // Переопределяем onCreate, чтобы сделать доп. действия при создании активити
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.di(this).inject(this);
    }

    private BasketActivityBinding binding;

    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = BasketActivityBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.basketItemsLiveData.observe(getViewLifecycleOwner(), basketItems -> {
            // Создание адаптера-itemViewAdapter, кладем обьект(который ведет себя так как мы описали
            // в классе BasketListAdapter в эту переменную
            // правила работы которого описаны в BasketListAdapter
            BasketListAdapter itemViewAdapter = new BasketListAdapter(viewModel);

            // Говорим какой список элементов корзины будет отображать тот адаптер который описали выше.
            // А в itemsToAdopt лежит потенциальный список ингр.
            itemViewAdapter.setItemsToAdopt(basketItems);

            // basketRecyclerView - это название и обращение к списку элементов корзины в xml
            // это переменная, в которой лежит описание того как отображаются ячейки списка
            // в переменную basketRecyclerView мы записываем тот адаптер который создали выше
            binding.basketRecyclerView.setAdapter(itemViewAdapter);
        });

        viewModel.goToProductDetails.observe(getViewLifecycleOwner(), product -> {
            Navigation.findNavController(view).navigate(
                    BasketFragmentDirections.actionBasketFragmentToProductDetailsFragment(product)
            );
        });
    }
}