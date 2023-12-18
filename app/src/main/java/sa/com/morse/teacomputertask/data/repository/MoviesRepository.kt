package sa.com.morse.teacomputertask.data.repository

import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.data.models.ActorsResponse
import sa.com.morse.teacomputertask.data.models.MovieDetailResponse
import sa.com.morse.teacomputertask.data.models.MoviesResponse
import sa.com.morse.teacomputertask.domain.repositories.IMoviesRepository
import sa.com.morse.teacomputertask.remote.MoviesApi

class MoviesRepository (private val remote : MoviesApi) : IMoviesRepository {
    override fun search(movieName : String): Observable<MoviesResponse> {
        return remote.searchForMovie(movieName)
    }

    override fun getList(): Observable<MoviesResponse> {
        return remote.loadPopularMovies()
    }

    override fun getDetails(movieId:Int): Observable<MovieDetailResponse> {
       return remote.loadMovieDetails(movieId)
    }

    override fun getActors(movieId: Int): Observable<ActorsResponse> {
        return remote.loadMovieActors(movieId)
    }
}