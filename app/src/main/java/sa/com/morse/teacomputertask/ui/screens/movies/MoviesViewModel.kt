package sa.com.morse.teacomputertask.ui.screens.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.observers.DisposableObserver
import sa.com.morse.teacomputertask.domain.usecases.LoadMoviesUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import sa.com.morse.teacomputertask.data.models.MoviesResponse
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.utils.State

class MoviesViewModel(private val moviesUseCase: LoadMoviesUseCase) : ViewModel() {

    private val _movies = MutableLiveData<State<ArrayList<MovieOrSeriesItem>>>()
    val movies: LiveData<State<ArrayList<MovieOrSeriesItem>>> get() = _movies
    private val disposableBag = CompositeDisposable()

    init {
        loadMovies()
    }
     fun loadMovies() {
        moviesUseCase.invoke()
            .subscribe { response ->
                _movies.postValue(response)
            }
            .addTo(disposableBag)
    }

    override fun onCleared() {
        super.onCleared()
        disposableBag.clear()
    }

}