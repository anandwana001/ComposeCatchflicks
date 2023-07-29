package com.akshay.composecatchflicks.domain.model

/**
 * Created by anandwana001 on
 * 16, December, 2022
 **/
data class Movie(
    val id: Int? = null,
    val title: String? = null,
    val overview: String? = null,
    val voteAverage: Float? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
)

