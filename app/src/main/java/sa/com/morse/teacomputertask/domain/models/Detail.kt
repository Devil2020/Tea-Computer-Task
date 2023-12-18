package sa.com.morse.teacomputertask.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import sa.com.morse.teacomputertask.data.models.ActorsResponse
import sa.com.morse.teacomputertask.utils.Constants

@Entity(tableName = Constants.detailTable)
data class Detail(
    val image: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val overview: String,
    val name: String,
    val video: Boolean = false,
    val date: String,
    val actors: Actors,
    val status: String
)

data class Actors(
    val items: List<ActorsResponse.User>
)