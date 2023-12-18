package sa.com.morse.teacomputertask.domain.usecases

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.domain.models.Detail
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.domain.repositories.ICommonRepository
import sa.com.morse.teacomputertask.utils.SingleUseCase
import sa.com.morse.teacomputertask.utils.State

class LoadMostSearchedDetailUseCase  (private val repo : ICommonRepository) : SingleUseCase<Int, Detail>() {
    override fun execute(args: Int?): Observable<State<Detail>> {
        return repo.getDetail(args!!)
            .map {
                State.Success(it)
            }
    }
}