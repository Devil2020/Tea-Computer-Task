package sa.com.morse.teacomputertask.data.models

import sa.com.morse.teacomputertask.domain.models.Actors
import sa.com.morse.teacomputertask.domain.models.Detail
import sa.com.morse.teacomputertask.utils.Constants

data class ASeriesOfDetailResponse(
    val adult: Boolean?,
    val backdrop_path: String,
    val created_by: List<CreatedBy?>?,
    val episode_run_time: List<Int?>?,
    val first_air_date: String,
    val genres: List<Genre?>?,
    val homepage: String?,
    val id: Int,
    val in_production: Boolean?,
    val languages: List<String?>?,
    val last_air_date: String?,
    val last_episode_to_air: LastEpisodeToAir?,
    val name: String,
    val networks: List<Network?>?,
    val next_episode_to_air: Any?,
    val number_of_episodes: Int?,
    val number_of_seasons: Int?,
    val origin_country: List<String?>?,
    val original_language: String?,
    val original_name: String?,
    val overview: String,
    val popularity: Double?,
    val poster_path: String?,
    val production_companies: List<ProductionCompany?>?,
    val production_countries: List<ProductionCountry?>?,
    val seasons: List<Season?>?,
    val spoken_languages: List<SpokenLanguage?>?,
    val status: String,
    val tagline: String?,
    val type: String?,
    val vote_average: Double?,
    val vote_count: Int?
) {

    private fun getBackgroundImage() = "${Constants.imageApiBackground}/$backdrop_path"
    fun toDetails(actors: ArrayList<ActorsResponse.User>) = Detail(getBackgroundImage() ,id , overview , name , false , first_air_date ,  Actors(actors) , status)

    data class CreatedBy(
        val credit_id: String?,
        val gender: Int?,
        val id: Int?,
        val name: String?,
        val profile_path: String?
    )

    data class Genre(
        val id: Int?,
        val name: String?
    )

    data class LastEpisodeToAir(
        val air_date: String?,
        val episode_number: Int?,
        val id: Int?,
        val name: String?,
        val overview: String?,
        val production_code: String?,
        val runtime: Int?,
        val season_number: Int?,
        val show_id: Int?,
        val still_path: String?,
        val vote_average: Double?,
        val vote_count: Int?
    )

    data class Network(
        val id: Int?,
        val logo_path: String?,
        val name: String?,
        val origin_country: String?
    )

    data class ProductionCompany(
        val id: Int?,
        val logo_path: String?,
        val name: String?,
        val origin_country: String?
    )

    data class ProductionCountry(
        val iso_3166_1: String?,
        val name: String?
    )

    data class Season(
        val air_date: String?,
        val episode_count: Int?,
        val id: Int?,
        val name: String?,
        val overview: String?,
        val poster_path: String?,
        val season_number: Int?,
        val vote_average: Double?
    )

    data class SpokenLanguage(
        val english_name: String?,
        val iso_639_1: String?,
        val name: String?
    )
}