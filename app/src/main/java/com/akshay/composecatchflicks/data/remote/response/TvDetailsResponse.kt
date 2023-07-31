package com.akshay.composecatchflicks.data.remote.response

import com.akshay.composecatchflicks.data.model.Genres
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by anandwana001 on
 * 31, July, 2023
 **/
@Serializable
data class TvDetailsResponse(
    @SerialName("popularity") val popularity: Float? = null,
    @SerialName("vote_count") val vote_count: Int? = null,
    @SerialName("poster_path") val poster_path: String? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("adult") val adult: Boolean? = null,
    @SerialName("backdrop_path") val backdrop_path: String? = null,
    @SerialName("original_language") val original_language: String? = null,
    @SerialName("original_name") val original_title: String? = null,
    @SerialName("name") val title: String? = null,
    @SerialName("genres") val genres: List<Genres>? = null,
    @SerialName("vote_average") val vote_average: Float? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("first_air_date") val first_air_date: String? = null,
    @SerialName("number_of_episodes") val number_of_episodes: Int? = null,
    @SerialName("number_of_seasons") val number_of_seasons: Int? = null,
    @SerialName("status") val status: String? = null,
)