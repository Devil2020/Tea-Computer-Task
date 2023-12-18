package sa.com.morse.teacomputertask.domain.usecases

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.data.models.SeriesResponse
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.domain.repositories.ISeriesRepository
import sa.com.morse.teacomputertask.utils.SingleUseCase
import sa.com.morse.teacomputertask.utils.State

class LoadSeriesUseCase (private val repo : ISeriesRepository) : SingleUseCase<Unit?, ArrayList<MovieOrSeriesItem>>() {
    override fun execute(args: Unit?): Observable<State<ArrayList<MovieOrSeriesItem>>> {
        return repo.getList()
            .map {
                State.Success(it.results.map { it.toMovieOrSeriesItem() } as ArrayList)
            }
    }
}