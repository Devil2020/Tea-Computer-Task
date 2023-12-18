package sa.com.morse.teacomputertask.ui.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.domain.usecases.SaveMovieOrSeriesUseCase
import sa.com.morse.teacomputertask.domain.usecases.SearchUseCase
import sa.com.morse.teacomputertask.utils.State

class SearchViewModel (private val seachUseCase: SearchUseCase , private val saveUseCase : SaveMovieOrSeriesUseCase) : ViewModel() {
    private val _items = MutableLiveData<State<ArrayList<MovieOrSeriesItem>>>()
    val items: LiveData<State<ArrayList<MovieOrSeriesItem>>> get() = _items
    private val disposableBag = CompositeDisposable()

    init {
        search("")
    }
    fun search(name : String) {
        seachUseCase.invoke(name)
            .subscribe { response ->
                _items.postValue(response)
            }
            .addTo(disposableBag)
    }

    fun save (item: MovieOrSeriesItem){
        saveUseCase.invoke(item).subscribe {  }.addTo(disposableBag)
    }
    override fun onCleared() {
        super.onCleared()
        disposableBag.clear()
    }
}