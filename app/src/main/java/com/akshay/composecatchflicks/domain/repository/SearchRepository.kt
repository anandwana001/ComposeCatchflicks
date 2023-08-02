package com.akshay.composecatchflicks.domain.repository

import com.akshay.composecatchflicks.data.remote.NetworkService
import com.akshay.composecatchflicks.util.ComposeCatchflicksNetworkResult
import com.akshay.composecatchflicks.util.ComposeCatchflicksResult
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 02, July, 2023
 **/
class SearchRepository @Inject constructor(
    private val networkService: NetworkService,
) {
    suspend fun getSearchResult(
        query: String,
        page: Int = 1
    ): ComposeCatchflicksResult<List<String>> =
        when (val networkResponse = networkService.searchMovies(
            query = query,
            language = "en",
            page = page
        )) {
            is ComposeCatchflicksNetworkResult.Failure -> ComposeCatchflicksResult.Error

            is ComposeCatchflicksNetworkResult.Success -> {
                ComposeCatchflicksResult.Success(
                    networkResponse.data.results.mapNotNull { it.title }
                )
            }
        }
}
