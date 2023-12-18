package sa.com.morse.teacomputertask.local


import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import sa.com.morse.teacomputertask.domain.models.Detail
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.utils.Constants

@Dao
interface RoomApi {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addMovieOrSeriesItem(item: MovieOrSeriesItem ) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addDetailItem(item: Detail ) : Completable

    @Query("SELECT * FROM ${Constants.detailTable} WHERE id = :id")
     fun getDetails(id: Int): Observable<Detail>

    @Query("SELECT * FROM ${Constants.moviesOrSeriesTable}")
     fun getMoviesOrSeries(): Observable<List<MovieOrSeriesItem>>


}