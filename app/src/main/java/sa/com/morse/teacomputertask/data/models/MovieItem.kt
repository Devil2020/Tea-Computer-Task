package sa.com.morse.teacomputertask.data.models

import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.utils.Constants

data class MovieItem(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int?>?,
    val id: Int,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
) {
    private fun getFullPosterPath() = "${Constants.imageApiPoster}$poster_path"
    fun toMovieOrSeriesItem() = MovieOrSeriesItem(id, getFullPosterPath(), release_date, title , true)
}