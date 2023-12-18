package sa.com.morse.teacomputertask.data.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.domain.models.Detail
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.domain.repositories.ICommonRepository
import sa.com.morse.teacomputertask.local.RoomApi

open class CommonRepository(private val cache: RoomApi) : ICommonRepository {
    override fun add(item: MovieOrSeriesItem): Observable<Long> {
        return cache.addMovieOrSeriesItem(item).toObservable()
    }

    override fun add(item: Detail): Observable<Long> {
        return cache.addDetailItem(item).toObservable()
    }

    override fun loadAll(): Observable<List<MovieOrSeriesItem>> {
        return cache.getMoviesOrSeries()
    }

    override fun getDetail(id: Int): Observable <Detail> {
        return cache.getDetails(id)
    }
}