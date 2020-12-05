package rusha.x

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val disposables = mutableListOf<Disposable>()

    protected fun <T> Single<T>.managedSubscribe(onSuccess: (T) -> Unit): Disposable {
        val disposable = this.subscribe(onSuccess)
        disposables.add(disposable)
        return disposable
    }

    override fun onCleared() {
        super.onCleared()
        disposables.forEach { disposable ->
            disposable.dispose()
        }
    }
}