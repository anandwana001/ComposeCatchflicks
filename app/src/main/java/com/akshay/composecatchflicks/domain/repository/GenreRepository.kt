package com.akshay.composecatchflicks.domain.repository

import com.akshay.composecatchflicks.data.remote.NetworkService
import com.akshay.composecatchflicks.domain.model.Genres
import com.akshay.composecatchflicks.util.ComposeCatchflicksNetworkResult
import com.akshay.composecatchflicks.util.ComposeCatchflicksResult
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
}
