package sa.com.morse.teacomputertask.domain.models

import sa.com.morse.teacomputertask.data.models.ActorsResponse

data class Detail(
    val image: String,
    val id: Int,
    val overview: String,
    val name: String,
    val video: Boolean = false,
    val date : String ,
    val actors : List<ActorsResponse.User> ,
    val status : String
)