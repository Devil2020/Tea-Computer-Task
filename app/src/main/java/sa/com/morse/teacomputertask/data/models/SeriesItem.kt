package sa.com.morse.teacomputertask.data.models

import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.utils.Constants

data class SeriesItem(
    val adult: Boolean?,
    val backdrop_path: String?,
    val first_air_date: String,
    val genre_ids: List<Int?>?,
    val id: Int,
    val name: String,
    val origin_country: List<String?>?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val vote_average: Double?,
    val vote_count: Int?
) {
    private fun getFullPosterPath() = "${Constants.imageApiPoster}$poster_path"
    fun toMovieOrSeriesItem() = MovieOrSeriesItem(id, getFullPosterPath(), first_air_date, name , false)
}