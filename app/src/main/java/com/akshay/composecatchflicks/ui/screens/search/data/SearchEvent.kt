package com.akshay.composecatchflicks.ui.screens.search.data

/**
 * Created by anandwana001 on
 * 01, July, 2023
 **/
sealed interface SearchEvent {
    data class OpenGenre(val id: Int) : SearchEvent
    data class SearchQuery(val query: String) : SearchEvent
    data object SearchClear : SearchEvent
}