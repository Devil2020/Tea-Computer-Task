package sa.com.morse.teacomputertask.local


import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import sa.com.morse.teacomputertask.domain.models.Detail
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.remote.RoomTypeConverters
import sa.com.morse.teacomputertask.utils.Constants

@TypeConverters(value = [RoomTypeConverters::class ])
@Database(entities = [MovieOrSeriesItem::class, Detail::class], version = 5, exportSchema = false)
abstract class RoomAgent : RoomDatabase() {
    abstract fun getContract() : RoomApi
    companion object {

        @Volatile
        private var instance: RoomAgent? = null

        private val LOCK = Any()

        private var isTesting = false

        @VisibleForTesting
        fun turnTestingMode() {
            isTesting = true
        }

         fun createGateway(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: if (isTesting) buildTestDatabase(context).also {
                instance = it
            } else buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            RoomAgent::class.java, Constants.databaseName
        )
            .fallbackToDestructiveMigration()
            .build()

        private fun buildTestDatabase(context: Context) =
            Room.inMemoryDatabaseBuilder(context, RoomAgent::class.java)
                .allowMainThreadQueries()
                .build()
    }

}
