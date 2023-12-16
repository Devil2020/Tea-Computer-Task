package sa.com.morse.teacomputertask.data.repository

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.data.models.ASeriesOfDetailResponse
import sa.com.morse.teacomputertask.data.models.MovieDetailResponse
import sa.com.morse.teacomputertask.data.models.MoviesResponse
import sa.com.morse.teacomputertask.data.models.SeriesResponse
import sa.com.morse.teacomputertask.domain.repositories.IMoviesRepository
import sa.com.morse.teacomputertask.domain.repositories.ISeriesRepository
import sa.com.morse.teacomputertask.remote.MoviesApi

class SeriesRepository(private val remote: MoviesApi) : ISeriesRepository {

    override fun search(seriesName: String): Observable<SeriesResponse> {
        return remote.searchForSeries(seriesName)
    }

    override fun getList(): Observable<SeriesResponse> {
        return remote.loadPopularSeries()
    }

    override fun getDetails(seriesId: Int): Observable<ASeriesOfDetailResponse> {
        return remote.loadSeriesDetails(seriesId)
    }
}