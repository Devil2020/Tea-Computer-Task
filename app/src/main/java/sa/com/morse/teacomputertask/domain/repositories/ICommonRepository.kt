package sa.com.morse.teacomputertask.domain.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.domain.models.Detail
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem

interface ICommonRepository {
    fun add(item: MovieOrSeriesItem): Observable<Long>
    fun add(item: Detail): Observable<Long>
    fun loadAll(): Observable<List<MovieOrSeriesItem>>
    fun getDetail(id: Int): Observable<Detail>
}