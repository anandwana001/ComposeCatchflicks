package com.akshay.composecatchflicks.domain.repository

import com.akshay.composecatchflicks.data.remote.NetworkService
import com.akshay.composecatchflicks.domain.model.Genres
import com.akshay.composecatchflicks.domain.model.Movie
import com.akshay.composecatchflicks.util.ComposeCatchflicksNetworkResult
import com.akshay.composecatchflicks.util.ComposeCatchflicksResult
import com.akshay.composecatchflicks.util.createPager
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 01, July, 2023
 **/
class GenreRepository @Inject constructor(private val networkService: NetworkService) {

    suspend fun getGenresList(): ComposeCatchflicksResult<List<Genres>> {
        return when (val networkResponse = networkService.getGenres(language = "en")) {
            is ComposeCatchflicksNetworkResult.Failure -> ComposeCatchflicksResult.Error

            is ComposeCatchflicksNetworkResult.Success -> {
                ComposeCatchflicksResult.Success(
                    networkResponse.data.genres.map {
                        Genres(id = it.id, name = it.name)
                    }
                )
            }
        }
    }

    fun getGenreDetail(
        genreId: Int,
    ) = createPager { page ->
        println("======> ${page}")
        when (val networkResponse = networkService.getGenreMovie(
            with_genres = genreId,
            page = page,
        )) {
            is ComposeCatchflicksNetworkResult.Failure -> {
                println("======> Failure ${networkResponse}")
                ComposeCatchflicksNetworkResult.Failure
            }

            is ComposeCatchflicksNetworkResult.Success -> {
                println("======> Success ${networkResponse}")
                ComposeCatchflicksNetworkResult.Success(Pair(
                    first = networkResponse.data.results?.map {
                        Movie(
                            id = it.id,
                            title = it.title,
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
}
