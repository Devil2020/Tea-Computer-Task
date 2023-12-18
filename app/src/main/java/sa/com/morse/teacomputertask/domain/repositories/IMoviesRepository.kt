package sa.com.morse.teacomputertask.domain.repositories

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.data.models.MovieDetailResponse
import sa.com.morse.teacomputertask.data.models.MoviesResponse
import sa.com.morse.teacomputertask.data.models.ActorsResponse

interface IMoviesRepository : ICommonRepository {
    fun search (movieName:String) : Observable<MoviesResponse>
    fun getList () : Observable<MoviesResponse>
    fun getDetails(movieId:Int) : Observable<MovieDetailResponse>
    fun getActors (movieId: Int) : Observable<ActorsResponse>

}