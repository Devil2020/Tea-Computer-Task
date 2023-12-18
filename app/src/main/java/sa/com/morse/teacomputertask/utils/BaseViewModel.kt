package sa.com.morse.teacomputertask.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem

open class BaseViewModel : ViewModel() {

    val disposableBag = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()
        disposableBag.dispose()
        disposableBag.clear()
    }
}