package com.akshay.composecatchflicks.domain.repository

import com.akshay.composecatchflicks.data.remote.NetworkService
import com.akshay.composecatchflicks.domain.model.Genres
import com.akshay.composecatchflicks.domain.model.Tv
import com.akshay.composecatchflicks.domain.model.TvDetail
import com.akshay.composecatchflicks.util.ComposeCatchflicksNetworkResult
import com.akshay.composecatchflicks.util.ComposeCatchflicksResult
import com.akshay.composecatchflicks.util.createPager
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 29, July, 2023
 **/
class TvRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun getTopRatedTv(
        language: String,
    ) = createPager { page ->
        when (val networkResponse = networkService.getTopRatedTv(
            language = language,
            page = page
        )) {
            is ComposeCatchflicksNetworkResult.Failure -> {
                ComposeCatchflicksNetworkResult.Failure
            }

            is ComposeCatchflicksNetworkResult.Success -> {
                ComposeCatchflicksNetworkResult.Success(Pair(
                    first = networkResponse.data.results?.map {
                        Tv(
                            id = it.id,
                            name = it.title,
                            overview = it.overview,
                            voteAverage = it.vote_average,
                            posterPath = it.poster_path,
                            backdropPath = it.backdrop_path
                        )
                    } ?: emptyList(),
                    second = networkResponse.data.total_pages ?: 0
                ))
            }
        }
    }.flow

    suspend fun getTvDetails(series_id: Int): ComposeCatchflicksResult<TvDetail> {
        return when (val networkResponse = networkService.getTvDetails(series_id = series_id)) {
            is ComposeCatchflicksNetworkResult.Failure -> ComposeCatchflicksResult.Error
            is ComposeCatchflicksNetworkResult.Success -> ComposeCatchflicksResult.Success(
                TvDetail(id = networkResponse.data.id,
                    title = networkResponse.data.title,
                    overview = networkResponse.data.overview,
                    voteAverage = networkResponse.data.vote_average,
                    posterPath = networkResponse.data.poster_path,
                    backdropPath = networkResponse.data.backdrop_path,
                    genres = networkResponse.data.genres?.filter {
                        it.name != null && it.id != null
                    }?.map {
                        Genres(it.id, it.name)
                    } ?: run {
                        emptyList()
                    })
            )
        }
    }
}