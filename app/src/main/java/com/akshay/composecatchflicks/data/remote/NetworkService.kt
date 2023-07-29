package com.akshay.composecatchflicks.data.remote

import com.akshay.composecatchflicks.BuildConfig
import com.akshay.composecatchflicks.data.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by anandwana001 on
 * 15, November, 2022
 **/
interface NetworkService {

    @GET(GENRE)
    suspend fun getGenres(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String?
    ): GenreResponse

    @GET(MOVIES_POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): PopularMoviesResponse

    @GET(MOVIES_NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): NowPlayingMoviesResponse

    @GET(MOVIES_UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): UpcomingMoviesResponse

    @GET(MOVIES_SEARCH)
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String?,
        @Query("query") query: String,
        @Query("page") page: Int?
    ): SearchMoviesResponse

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): MovieDetailResponse

    @GET(TV_TOP_RATED)
    suspend fun getTopRatedTv(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): UpcomingMoviesResponse
}
