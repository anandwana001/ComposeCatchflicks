package com.akshay.composecatchflicks.data.remote.response

import com.akshay.composecatchflicks.data.model.Genres
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by anandwana001 on
 * 15, November, 2022
 **/
@Serializable
data class GenreResponse(
    @SerialName("genres") val genres: List<Genres> = emptyList(),
)