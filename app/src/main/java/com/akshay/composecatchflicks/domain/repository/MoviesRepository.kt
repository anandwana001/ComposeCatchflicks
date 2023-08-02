package com.akshay.composecatchflicks.domain.repository

import com.akshay.composecatchflicks.data.remote.NetworkService
import com.akshay.composecatchflicks.domain.model.Genres
import com.akshay.composecatchflicks.domain.model.Movie
import com.akshay.composecatchflicks.domain.model.MovieDetail
import com.akshay.composecatchflicks.util.ComposeCatchflicksNetworkResult
import com.akshay.composecatchflicks.util.ComposeCatchflicksResult
import com.akshay.composecatchflicks.util.createPager
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 15, November, 2022
 **/
class MoviesRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun getPopularMovies(
        language: String,
    ) = createPager { page ->
        when (val networkResponse = networkService.getPopularMovies(
            language = language,
            page = page
        )) {
            is ComposeCatchflicksNetworkResult.Failure -> {
                ComposeCatchflicksNetworkResult.Failure
            }

            is ComposeCatchflicksNetworkResult.Success -> {
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

    fun getNowPlayingMovies(
        language: String,
    ) = createPager { page ->
        when (val networkResponse = networkService.getNowPlayingMovies(
            language = language,
            page = page
        )) {
            is ComposeCatchflicksNetworkResult.Failure -> {
                ComposeCatchflicksNetworkResult.Failure
            }

            is ComposeCatchflicksNetworkResult.Success -> {
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

    fun getUpcomingMovies(
        language: String,
    ) = createPager { page ->
        when (val networkResponse = networkService.getUpcomingMovies(
            language = language,
            page = page
        )) {
            is ComposeCatchflicksNetworkResult.Failure -> {
                ComposeCatchflicksNetworkResult.Failure
            }

            is ComposeCatchflicksNetworkResult.Success -> {
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

    suspend fun getMovieDetails(movieId: Int): ComposeCatchflicksResult<MovieDetail> {
        return when (val networkResponse = networkService.getMovieDetails(movieId = movieId)) {
            is ComposeCatchflicksNetworkResult.Failure -> ComposeCatchflicksResult.Error
            is ComposeCatchflicksNetworkResult.Success -> ComposeCatchflicksResult.Success(
                MovieDetail(
                    id = networkResponse.data.id,
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
                    }
                )
            )
        }
    }
}
