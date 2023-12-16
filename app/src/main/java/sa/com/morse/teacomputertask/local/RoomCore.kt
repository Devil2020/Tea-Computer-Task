package sa.com.morse.teacomputertask.local

/*
import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohammedmorse.differencebetweendatabaselibraries.gateways.Game
import com.mohammedmorse.local.database.DataBaseConstants

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class RoomCore : RoomDatabase() {

    abstract fun gamesDao() : RoomContract

    companion object {

        @Volatile private var instance: RoomCore? = null

        private val LOCK = Any()

       private var isTesting = false

        @VisibleForTesting
        fun turnTestingMode (){
            isTesting = true
        }

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: if(isTesting) buildTestDatabase(context).also {
                instance = it
            } else buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context ,
            RoomCore::class.java, DataBaseConstants.Table.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

        private fun buildTestDatabase(context: Context) =
            Room.inMemoryDatabaseBuilder(context, RoomCore::class.java)
                .allowMainThreadQueries()
                .build()
    }

}*/
