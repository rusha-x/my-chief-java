package rusha.x.basket;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rusha.x.BaseViewModel;
import rusha.x.SingleLiveEvent;
import rusha.x.product.Product;

public class BasketViewModel extends BaseViewModel {

    @Inject
    public BasketViewModel() {
        super();
    }

    public MutableLiveData<List<BasketItem>> basketItemsLiveData = new MutableLiveData<>(Collections.emptyList());
    public SingleLiveEvent<Product> goToProductDetails = new SingleLiveEvent<>();

    public void onBasketItemClick(@NonNull BasketItem item) {
        goToProductDetails.setValue(item.product);
    }
}