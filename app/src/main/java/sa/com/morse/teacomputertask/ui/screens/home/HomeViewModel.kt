package sa.com.morse.teacomputertask.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import sa.com.morse.teacomputertask.domain.models.Detail
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.domain.usecases.LoadMostSearchedListUseCase
import sa.com.morse.teacomputertask.utils.State
import sa.com.morse.teacomputertask.utils.onFalse
import sa.com.morse.teacomputertask.utils.onTrue

class HomeViewModel(private val useCase: LoadMostSearchedListUseCase) : ViewModel() {
    private val _searchItems = MutableLiveData<State<ArrayList<MovieOrSeriesItem>>>()
    val searchItems: LiveData<State<ArrayList<MovieOrSeriesItem>>> get() = _searchItems
    private val disposableBag = CompositeDisposable()

    init {
        loadMostSearched()
    }

    private fun loadMostSearched() {

        useCase.invoke().subscribe { response ->
            _searchItems.postValue(response)
        }.addTo(disposableBag)


    }

    override fun onCleared() {
        super.onCleared()
        disposableBag.clear()
    }
}