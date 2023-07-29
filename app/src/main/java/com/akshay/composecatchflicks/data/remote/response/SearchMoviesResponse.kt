package com.akshay.composecatchflicks.data.remote.response

import com.akshay.composecatchflicks.data.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Created by anandwana001 on
 * 15, November, 2022
 **/
@Serializable
data class SearchMoviesResponse(
    @SerialName("page") val page: Int,
    @SerialName("total_results") val totalResults: Int,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("results") val results: List<Movie>,
)