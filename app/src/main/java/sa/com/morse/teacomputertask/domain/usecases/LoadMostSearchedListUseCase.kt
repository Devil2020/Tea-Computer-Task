package sa.com.morse.teacomputertask.domain.usecases

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.domain.repositories.ICommonRepository
import sa.com.morse.teacomputertask.utils.SingleUseCase
import sa.com.morse.teacomputertask.utils.State

class LoadMostSearchedListUseCase  (private val repo : ICommonRepository) : SingleUseCase<Unit, ArrayList<MovieOrSeriesItem>>() {
    override fun execute(args: Unit?): Observable<State<ArrayList<MovieOrSeriesItem>>> {
        return repo.loadAll()
            .map {
                State.Success(it as ArrayList)
            }
    }
}