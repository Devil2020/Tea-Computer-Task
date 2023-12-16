package sa.com.morse.teacomputertask.domain.usecases

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.data.models.MoviesResponse
import sa.com.morse.teacomputertask.data.models.SeriesResponse
import sa.com.morse.teacomputertask.domain.repositories.IMoviesRepository
import sa.com.morse.teacomputertask.domain.repositories.ISeriesRepository
import sa.com.morse.teacomputertask.utils.base.SingleUseCase
import sa.com.morse.teacomputertask.utils.base.State

class LoadSeriesUseCase (private val repo : ISeriesRepository) : SingleUseCase<Unit? , SeriesResponse>() {
    override fun execute(args: Unit?): Observable<State<SeriesResponse>> {
        return repo.getList()
            .map { State.Success(it) }
    }
}