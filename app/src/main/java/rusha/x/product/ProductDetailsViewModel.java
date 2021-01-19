package rusha.x.product;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import rusha.x.BaseViewModel;

public class ProductDetailsViewModel extends BaseViewModel {

    @Inject
    public ProductDetailsViewModel() {
        super();
    }

    public MutableLiveData<String> nameLiveData = new MutableLiveData<>("");
    public MutableLiveData<String> formattedPriceLiveData = new MutableLiveData<>("");

    public MutableLiveData<ShowWelcomeParams> showWelcomeLiveData = new MutableLiveData<>(null);

    public void init(Product product) {
        nameLiveData.setValue(product.name);
        formattedPriceLiveData.setValue(product.price + "/" + product.unit);
    }

    public void onResume() {
        showWelcomeLiveData.setValue(new ShowWelcomeParams("Привет!"));
        showWelcomeLiveData.setValue(null);
    }
}