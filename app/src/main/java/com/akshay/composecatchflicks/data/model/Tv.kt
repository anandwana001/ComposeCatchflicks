package com.akshay.composecatchflicks.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by anandwana001 on
 * 29, July, 2023
 **/
@Serializable
data class Tv(
    @SerialName("id") val id: Int? = null,
    @SerialName("backdrop_path") val backdrop_path: String? = null,
    @SerialName("original_language") val original_language: String? = null,
    @SerialName("original_name") val original_name: String? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("popularity") val popularity: Float? = null,
    @SerialName("vote_count") val vote_count: Int? = null,
    @SerialName("first_air_date") val video: Boolean? = null,
    @SerialName("poster_path") val poster_path: String? = null,
    @SerialName("genre_ids") val genre_ids: List<Int>? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("vote_average") val vote_average: Float? = null,
)
