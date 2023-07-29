package com.akshay.composecatchflicks.domain.repository

import com.akshay.composecatchflicks.data.remote.NetworkService
import com.akshay.composecatchflicks.domain.model.Genres
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 01, July, 2023
 **/
class GenreRepository @Inject constructor(private val networkService: NetworkService) {

    suspend fun getGenresList() = networkService.getGenres(language = "en").genres.map {
        Genres(id = it.id, name = it.name)
    }
}
