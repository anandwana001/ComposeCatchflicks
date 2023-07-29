package com.akshay.composecatchflicks.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by anandwana001 on
 * 02, January, 2023
 **/
@Serializable
data class Genres(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
)
