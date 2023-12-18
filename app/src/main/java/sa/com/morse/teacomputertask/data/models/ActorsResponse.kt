package sa.com.morse.teacomputertask.data.models

import sa.com.morse.teacomputertask.utils.Constants

data class ActorsResponse(
    val id: Int,
    val cast: ArrayList<User>,
    val crew: ArrayList<User>
) {
    data class User(
        val id: Int,
        val name: String,
        val profile_path: String
    ) {
        fun getPosterPath() = "${Constants.imageApiPoster}/$profile_path"
    }
}