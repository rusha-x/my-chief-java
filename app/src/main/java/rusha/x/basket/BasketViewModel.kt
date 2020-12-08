package rusha.x.basket

import androidx.lifecycle.MutableLiveData
import rusha.x.BaseViewModel
import rusha.x.SingleLiveEvent
import rusha.x.product.Product
import javax.inject.Inject

class BasketViewModel @Inject constructor(): BaseViewModel() {
    val basketItemsLiveData = MutableLiveData<List<Basket.Item>>(emptyList())
    val goToProductDetails = SingleLiveEvent<Product>()

    fun onBasketItemClick(item: Basket.Item) {
        goToProductDetails.value = item.product
    }
}