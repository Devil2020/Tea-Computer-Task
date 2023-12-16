package sa.com.morse.teacomputertask.domain.repositories

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.data.models.MovieDetailResponse
import sa.com.morse.teacomputertask.data.models.MoviesResponse

interface IMoviesRepository {
    fun search (movieName:String) : Observable<MoviesResponse>
    fun getList () : Observable<MoviesResponse>
    fun getDetails(movieId:Int) : Observable<MovieDetailResponse>
}