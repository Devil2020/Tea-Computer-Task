package sa.com.morse.teacomputertask.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import sa.com.morse.teacomputertask.utils.Constants

@Entity(tableName = Constants.moviesOrSeriesTable)
data class MovieOrSeriesItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val date: String,
    val name: String,
    val isMovie: Boolean
)
