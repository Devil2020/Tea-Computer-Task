package sa.com.morse.teacomputertask.domain.usecases

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.data.models.MovieDetailResponse
import sa.com.morse.teacomputertask.data.models.MoviesResponse
import sa.com.morse.teacomputertask.domain.repositories.IMoviesRepository
import sa.com.morse.teacomputertask.utils.base.SingleUseCase
import sa.com.morse.teacomputertask.utils.base.State

class LoadMovieDetailUseCase (private val repo : IMoviesRepository) : SingleUseCase<Int , MovieDetailResponse>() {
    override fun execute(args: Int?): Observable<State<MovieDetailResponse>> {
        return repo.getDetails(args!!)
            .map { State.Success(it) }
    }
}