package sa.com.morse.teacomputertask.domain.usecases

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.data.models.ActorsResponse
import sa.com.morse.teacomputertask.data.models.MovieDetailResponse
import sa.com.morse.teacomputertask.domain.models.Detail
import sa.com.morse.teacomputertask.domain.repositories.IMoviesRepository
import sa.com.morse.teacomputertask.utils.SingleUseCase
import sa.com.morse.teacomputertask.utils.State

class LoadMovieDetailUseCase(private val repo: IMoviesRepository) : SingleUseCase<Int, Detail>() {
    override fun execute(args: Int?): Observable<State<Detail>> {
        return Observable.zip(repo.getDetails(args!!), repo.getActors(args)) { details, actors ->
            State.Success(details.toDetails(arrayListOf<ActorsResponse.User>().apply {
                addAll(actors.cast)
                addAll(actors.crew)
            }))
        }

    }
}