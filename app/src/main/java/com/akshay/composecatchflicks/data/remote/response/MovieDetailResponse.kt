package com.akshay.composecatchflicks.data.remote.response

import com.akshay.composecatchflicks.data.model.Genres
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by anandwana001 on
 * 02, January, 2023
 **/
@Serializable
data class MovieDetailResponse(
    @SerialName("popularity") val popularity: Float? = null,
    @SerialName("vote_count") val vote_count: Int? = null,
    @SerialName("video") val video: Boolean? = null,
    @SerialName("poster_path") val poster_path: String? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("adult") val adult: Boolean? = null,
    @SerialName("backdrop_path") val backdrop_path: String? = null,
    @SerialName("original_language") val original_language: String? = null,
    @SerialName("original_title") val original_title: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("genres") val genres: List<Genres>? = null,
    @SerialName("vote_average") val vote_average: Float? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("release_date") val release_date: String? = null,
)
