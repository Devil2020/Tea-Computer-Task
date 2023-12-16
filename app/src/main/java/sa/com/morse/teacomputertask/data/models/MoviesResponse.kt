package sa.com.morse.teacomputertask.data.models

data class MoviesResponse(
    val page: Int?,
    val results: List<MovieItem?>?,
    val total_pages: Int?,
    val total_results: Int?
)