package sa.com.morse.teacomputertask.domain.usecases

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.domain.repositories.ICommonRepository
import sa.com.morse.teacomputertask.domain.repositories.IMoviesRepository
import sa.com.morse.teacomputertask.utils.SingleUseCase
import sa.com.morse.teacomputertask.utils.State

class SaveMovieOrSeriesUseCase  (private val repo : ICommonRepository) : SingleUseCase<MovieOrSeriesItem, Long>() {
    override fun execute(args: MovieOrSeriesItem?): Observable<State<Long>> {
        return repo.add(args!!)
            .map {
                State.Success(it)
            }
    }
}