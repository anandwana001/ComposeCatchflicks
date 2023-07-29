package com.akshay.composecatchflicks.domain.repository

import com.akshay.composecatchflicks.data.remote.NetworkService
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 02, July, 2023
 **/
class SearchRepository @Inject constructor(
    private val networkService: NetworkService,
) {
    suspend fun getSearchResult(query: String, page: Int = 1): List<String> =
        networkService.searchMovies(
            query = query,
            language = "en",
            page = page
        ).results.mapNotNull { it.title }

}
