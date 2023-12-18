package sa.com.morse.teacomputertask.ui.screens.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.domain.usecases.LoadSeriesUseCase
import sa.com.morse.teacomputertask.utils.State

class SeriesViewModel (private val seriesUseCase : LoadSeriesUseCase) : ViewModel() {

    private val _series = MutableLiveData<State<ArrayList<MovieOrSeriesItem>>>()
    val series: LiveData<State<ArrayList<MovieOrSeriesItem>>> get() = _series
    private val disposableBag = CompositeDisposable()

    init {
        loadSeries()
    }
    fun loadSeries() {
        seriesUseCase.invoke()
            .subscribe { response ->
                _series.postValue(response)
            }
            .addTo(disposableBag)
    }

    override fun onCleared() {
        super.onCleared()
        disposableBag.clear()
    }

}