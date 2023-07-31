package com.akshay.composecatchflicks.domain.model

/**
 * Created by anandwana001 on
 * 31, July, 2023
 **/
data class TvDetail(
    val id: Int? = null,
    val title: String? = null,
    val overview: String? = null,
    val voteAverage: Float? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val genres: List<Genres> = emptyList()
)
