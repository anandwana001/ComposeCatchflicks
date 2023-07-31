package com.akshay.composecatchflicks.domain.repository

import com.akshay.composecatchflicks.data.remote.NetworkService
import com.akshay.composecatchflicks.domain.model.Genres
import com.akshay.composecatchflicks.domain.model.Tv
import com.akshay.composecatchflicks.domain.model.TvDetail
import com.akshay.composecatchflicks.util.createPager
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 29, July, 2023
 **/
class TvRepository@Inject constructor(
    private val networkService: NetworkService
) {

    fun getTopRatedTv(
        language: String,
    ) = createPager { page ->
        val data = networkService.getTopRatedTv(
            language = language,
            page = page
        )
        Pair(
            first = data.results?.map {
                Tv(
                    id = it.id,
                    name = it.title,
                    overview = it.overview,
                    voteAverage = it.vote_average,
                    posterPath = it.poster_path,
                    backdropPath = it.backdrop_path
                )
            } ?: emptyList(),
            second = data.total_pages ?: 0
        )
    }.flow

    suspend fun getTvDetails(series_id: Int): TvDetail {
        val data = networkService.getTvDetails(series_id = series_id)
        return TvDetail(
            id = data.id,
            title = data.title,
            overview = data.overview,
            voteAverage = data.vote_average,
            posterPath = data.poster_path,
            backdropPath = data.backdrop_path,
            genres = data.genres?.filter {
                it.name != null && it.id != null
            }?.map {
                Genres(it.id, it.name)
            } ?: run {
                emptyList()
            }
        )
    }
}