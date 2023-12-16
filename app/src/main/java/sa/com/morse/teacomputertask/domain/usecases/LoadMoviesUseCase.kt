package sa.com.morse.teacomputertask.domain.usecases

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.data.models.MoviesResponse
import sa.com.morse.teacomputertask.domain.repositories.IMoviesRepository
import sa.com.morse.teacomputertask.utils.base.SingleUseCase
import sa.com.morse.teacomputertask.utils.base.State

class LoadMoviesUseCase (private val repo : IMoviesRepository) : SingleUseCase<Unit? , MoviesResponse>() {
    override fun execute(args: Unit?): Observable<State<MoviesResponse>> {
        return repo.getList()
            .map { State.Success(it) }
    }
}