package sa.com.morse.teacomputertask.domain.usecases

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.zipWith
import sa.com.morse.teacomputertask.data.repository.MoviesRepository
import sa.com.morse.teacomputertask.data.repository.SeriesRepository
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.domain.repositories.IMoviesRepository
import sa.com.morse.teacomputertask.domain.repositories.ISeriesRepository
import sa.com.morse.teacomputertask.utils.SingleUseCase
import sa.com.morse.teacomputertask.utils.State

class SearchUseCase(
    private val moviesRepository: IMoviesRepository,
    private val seriesRepository: ISeriesRepository
) : SingleUseCase<String?, ArrayList<MovieOrSeriesItem>>() {
    override fun execute(args: String?): Observable<State<ArrayList<MovieOrSeriesItem>>> {
        return Observable.merge(moviesRepository.search(args ?: "")
            .map {
                it.results.map { it.toMovieOrSeriesItem() } as ArrayList
            }, seriesRepository.search(args ?: "")
            .map {
                it.results.map { it.toMovieOrSeriesItem() } as ArrayList
            }).map {
            State.Success(it)
        }
    }
}