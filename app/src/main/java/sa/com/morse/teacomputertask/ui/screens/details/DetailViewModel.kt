package sa.com.morse.teacomputertask.ui.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import sa.com.morse.teacomputertask.domain.models.Detail
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.domain.usecases.LoadASeriesOfDetailUseCase
import sa.com.morse.teacomputertask.domain.usecases.LoadMovieDetailUseCase
import sa.com.morse.teacomputertask.utils.State
import sa.com.morse.teacomputertask.utils.onFalse
import sa.com.morse.teacomputertask.utils.onTrue

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val movieUseCase: LoadMovieDetailUseCase,
    private val seriesUseCase: LoadASeriesOfDetailUseCase
) : ViewModel() {
    val id: Int? by lazy {
        savedStateHandle.get<Int>("id")
    }
    val isMovie: Boolean by lazy {
        savedStateHandle.get<Boolean>("isMovie") ?: true
    }

    private val _details = MutableLiveData<State<Detail>>()
    val details: LiveData<State<Detail>> get() = _details
    private val disposableBag = CompositeDisposable()

    init {
        loadDetails()
    }

    fun loadDetails() {
        isMovie
            .onTrue {
                movieUseCase.invoke(id).subscribe { response ->
                    _details.postValue(response)
                }.addTo(disposableBag)
            }
            .onFalse {
                seriesUseCase.invoke(id).subscribe { response ->
                    _details.postValue(response)
                }.addTo(disposableBag)
            }


    }

    override fun onCleared() {
        super.onCleared()
        disposableBag.clear()
    }
}