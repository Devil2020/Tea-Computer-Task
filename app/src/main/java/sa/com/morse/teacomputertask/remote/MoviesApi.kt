package sa.com.morse.teacomputertask.remote

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sa.com.morse.teacomputertask.data.models.ASeriesOfDetailResponse
import sa.com.morse.teacomputertask.data.models.MovieDetailResponse
import sa.com.morse.teacomputertask.data.models.MoviesResponse
import sa.com.morse.teacomputertask.data.models.SeriesResponse
import sa.com.morse.teacomputertask.data.models.ActorsResponse

interface MoviesApi {

    @GET("search/movie")
    fun searchForMovie(
        @Query("query") name: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Observable<MoviesResponse>

    @GET("search/tv")
    fun searchForSeries(
        @Query("query") name: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Observable<SeriesResponse>

    @GET("trending/tv/day?language=en-US&page=1")
    fun loadPopularSeries(): Observable<SeriesResponse>

    @GET("tv/{movieId}?language=en-US")
    fun loadSeriesDetails(@Path("movieId") id: Int): Observable<ASeriesOfDetailResponse>

    @GET("movie/{movieId}/credits?language=en-US&page=1")
    fun loadMovieActors(@Path("movieId") movieId: Int): Observable<ActorsResponse>

    @GET("tv/{tvId}/credits?language=en-US&page=1")
    fun loadASeriesOfActors(@Path("tvId") tvId: Int): Observable<ActorsResponse>

    @GET("trending/movie/day?language=en-US&page=1")
    fun loadPopularMovies(): Observable<MoviesResponse>

    @GET("movie/{movieId}?language=en-US")
    fun loadMovieDetails(@Path("movieId") id: Int): Observable<MovieDetailResponse>

}