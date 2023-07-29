package com.akshay.composecatchflicks.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by anandwana001 on
 * 15, November, 2022
 **/
@Serializable
data class Dates(
    @SerialName("maximum") val maximum: String,
    @SerialName("minimum") val minimum: String
)