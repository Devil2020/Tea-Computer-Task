package sa.com.morse.teacomputertask.domain.usecases

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.data.models.ASeriesOfDetailResponse
import sa.com.morse.teacomputertask.data.models.MovieDetailResponse
import sa.com.morse.teacomputertask.data.models.MoviesResponse
import sa.com.morse.teacomputertask.domain.repositories.IMoviesRepository
import sa.com.morse.teacomputertask.domain.repositories.ISeriesRepository
import sa.com.morse.teacomputertask.utils.base.SingleUseCase
import sa.com.morse.teacomputertask.utils.base.State

class LoadASeriesOfDetailUseCase (private val repo : ISeriesRepository) : SingleUseCase<Int , ASeriesOfDetailResponse>() {
    override fun execute(args: Int?): Observable<State<ASeriesOfDetailResponse>> {
        return repo.getDetails(args!!)
            .map { State.Success(it) }
    }
}