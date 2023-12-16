package sa.com.morse.teacomputertask.data.models

data class SeriesResponse(
    val page: Int?,
    val results: List<SeriesItem?>?,
    val total_pages: Int?,
    val total_results: Int?
)