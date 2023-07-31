package com.akshay.composecatchflicks.data.remote.response

import com.akshay.composecatchflicks.data.model.Tv
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by anandwana001 on
 * 31, July, 2023
 **/
@Serializable
data class TopRatedTvResponse(
    @SerialName("page") val page: Int? = null,
    @SerialName("total_pages") val total_pages: Int? = null,
    @SerialName("total_results") val total_results: Int? = null,
    @SerialName("results") val results: List<Tv>? = null,
)