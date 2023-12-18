package sa.com.morse.teacomputertask.domain.models

data class MovieOrSeriesItem(
    val id: Int,
    val image: String,
    val date: String,
    val name: String,
    val isMovie  :Boolean
)
