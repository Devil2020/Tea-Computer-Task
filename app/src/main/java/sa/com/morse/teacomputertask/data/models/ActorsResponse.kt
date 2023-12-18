package sa.com.morse.teacomputertask.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import sa.com.morse.teacomputertask.utils.Constants

data class ActorsResponse(
    val id: Int,
    val cast: ArrayList<User>,
    val crew: ArrayList<User>
) {
    data class User(
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val name: String,
        val profile_path: String
    ) {
        fun getPosterPath() = "${Constants.imageApiPoster}/$profile_path"
    }
}