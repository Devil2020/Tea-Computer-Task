package sa.com.morse.teacomputertask.local
/*

import androidx.room.*
import com.mohammedmorse.differencebetweendatabaselibraries.gateways.Game
import com.mohammedmorse.local.database.DataBaseConstants

@Dao
interface RoomContract {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGameItem(game: Game ) : Long

    @Query("SELECT * FROM ${DataBaseConstants.GameEntry.TABLE_NAME} WHERE id = :id")
    suspend fun getGameItem(id: Int): Game

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGamesItems(weather: List<Game>) : List<Long>

    @Query("SELECT * FROM ${DataBaseConstants.GameEntry.TABLE_NAME}")
    suspend fun getGamesItems(): List<Game>

    @Update
    suspend fun updateGameItem(weather: Game): Int

    @Query("DELETE FROM ${DataBaseConstants.GameEntry.TABLE_NAME} WHERE id = :id")
    suspend fun deleteGameItemById(id : Int) : Int

    @Query("SELECT COUNT(*) FROM ${DataBaseConstants.GameEntry.TABLE_NAME} ")
    suspend fun getGamesCount () : Int

    @Query("SELECT Exists ( SELECT * FROM ${DataBaseConstants.GameEntry.TABLE_NAME} WHERE id == :gameId )")
    suspend fun isGameExistInDataBase ( gameId : Int ) : Boolean

    @Delete
    suspend fun deleteGameItem(weather : Game) : Int

    @Query("DELETE FROM ${DataBaseConstants.GameEntry.TABLE_NAME}")
    suspend fun clearCachedGamesItems(): Int

}*/
