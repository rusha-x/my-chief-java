package rusha.x.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rusha.x.product.Product
import rusha.x.SingleLiveEvent

class BasketViewModel : ViewModel() {
    val basketItemsLiveData = MutableLiveData<List<Basket.Item>>(emptyList())
    val goToProductDetails = SingleLiveEvent<Product>()

    fun onBasketItemClick(item: Basket.Item) {
        goToProductDetails.value = item.product
    }
}