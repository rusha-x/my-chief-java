package rusha.x.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import rusha.x.R
import rusha.x.databinding.BasketActivityBinding
import rusha.x.di
import javax.inject.Inject

// класс BasketActivity наследует все свойства Fragment т.е все его переменные и функции
// TODO для каждого BasketActivity, являющегося AppCompatActivity
class BasketFragment : Fragment() {

    @Inject
    lateinit var viewModel: BasketViewModel

    // мы переопределяем onCreate, чтобы сделать доп. действия при создании активити
    // TODO определяем, что в отличие от других AppCompatActivity, BasketActivity onCreate
    override fun onCreate(
        // TODO , принимая savedInstanceState, являющееся необязательным Bundle,
        savedInstanceState: Bundle?
    ) {
        // TODO сначала делает onCreate как AppCompatActivity с savedInstanceState
        super.onCreate(savedInstanceState)
        di().inject(this)    }

    private lateinit var binding: BasketActivityBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BasketActivityBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.basketItemsLiveData.observe(viewLifecycleOwner, Observer { basketItems ->
            //создание адаптера-itemViewAdapter, кладем обьект(который ведет себя так как мы описали
            // в классе BasketListAdapter   типа BasketListAdapter в эту переменную
            //правила работы которого описаны в BasketListAdapter
            //TODO itemViewAdapter будет получен в результате создания BasketListAdapter
            val itemViewAdapter = BasketListAdapter(viewModel)

            // говорим какой список элементов корзины будет отображать тот адаптер который описали выше.
            // А в itemsToAdopt лежит потенциальный список ингр.
            // TODO itemsToAdopt itemViewAdapter(-a) это теперь тоже самое что и basketItems
            itemViewAdapter.itemsToAdopt = basketItems

            //basketRecyclerView- это название и обращение к списку элементов корзины в xml
            //это переменная, в которой лежит описание того как отображаются ячейки списка
            //в переменную basketRecyclerView мы записываем тот адаптер который создали выше
            //TODO adapter basketRecyclerView тоже самое itemViewAdapter
            binding.basketRecyclerView.adapter = itemViewAdapter
        })

        viewModel.goToProductDetails.observe(viewLifecycleOwner, Observer { product ->
            findNavController().navigate(
                BasketFragmentDirections.actionBasketFragmentToProductDetailsFragment(product)
            )
        })
    }
}