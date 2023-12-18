package sa.com.morse.teacomputertask.domain.repositories

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.data.models.ASeriesOfDetailResponse
import sa.com.morse.teacomputertask.data.models.ActorsResponse
import sa.com.morse.teacomputertask.data.models.MovieDetailResponse
import sa.com.morse.teacomputertask.data.models.MoviesResponse
import sa.com.morse.teacomputertask.data.models.SeriesResponse

interface ISeriesRepository {
    fun search (seriesName : String) : Observable<SeriesResponse>
    fun getList () : Observable<SeriesResponse>
    fun getDetails(seriesId : Int) : Observable<ASeriesOfDetailResponse>
    fun getActors (seriesId: Int) : Observable<ActorsResponse>
}