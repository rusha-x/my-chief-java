package rusha.x;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {

    private List<Disposable> disposables = new ArrayList<>();

    protected void disposeOnExit(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void onCleared() {
        super.onCleared();
        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
    }
}