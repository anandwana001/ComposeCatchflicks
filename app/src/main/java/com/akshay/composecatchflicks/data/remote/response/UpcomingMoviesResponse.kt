package com.akshay.composecatchflicks.data.remote.response

import com.akshay.composecatchflicks.data.model.Dates
import com.akshay.composecatchflicks.data.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by anandwana001 on
 * 15, November, 2022
 **/
@Serializable
data class UpcomingMoviesResponse(
    @SerialName("page") val page: Int? = null,
    @SerialName("total_pages") val total_pages: Int? = null,
    @SerialName("total_results") val total_results: Int? = null,
    @SerialName("results") val results: List<Movie>? = null,
    @SerialName("dates") val dates: Dates? = null,
)