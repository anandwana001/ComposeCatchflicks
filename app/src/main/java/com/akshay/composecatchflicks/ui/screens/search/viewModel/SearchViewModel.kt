package com.akshay.composecatchflicks.ui.screens.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.composecatchflicks.domain.repository.GenreRepository
import com.akshay.composecatchflicks.domain.repository.SearchRepository
import com.akshay.composecatchflicks.ui.screens.search.data.SearchEvent
import com.akshay.composecatchflicks.ui.screens.search.data.SearchState
import com.akshay.composecatchflicks.ui.theme.Pink40
import com.akshay.composecatchflicks.ui.theme.Pink80
import com.akshay.composecatchflicks.ui.theme.Purple40
import com.akshay.composecatchflicks.ui.theme.Purple80
import com.akshay.composecatchflicks.ui.theme.PurpleGrey40
import com.akshay.composecatchflicks.ui.theme.PurpleGrey80
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 01, July, 2023
 **/
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _searchStateData = MutableStateFlow(SearchState())
    val searchStateData = _searchStateData.asStateFlow()

    private val _searchEvent = MutableSharedFlow<SearchEvent>()
    val searchEvent = _searchEvent.asSharedFlow()

    suspend fun emitEvent(event: SearchEvent) = _searchEvent.emit(event)

    init {
        _searchEvent.onEach {
            listenEvent(it)
        }.launchIn(viewModelScope)
        setupSearchScreen()
    }

    private fun listenEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchQuery -> {
                viewModelScope.launch {
                    _searchStateData.update {
                        it.copy(searchTextField = event.query)
                    }
                    _searchStateData.update {
                        it.copy(searchResult = searchRepository.getSearchResult(event.query))
                    }
                }
            }
            is SearchEvent.OpenGenre -> {

            }
            is SearchEvent.SearchClear -> {
                _searchStateData.update {
                    it.copy(
                        searchResult = emptyList(),
                        searchTextField = ""
                    )
                }
            }
        }
    }

    private fun setupSearchScreen() {
        viewModelScope.launch {
            _searchStateData.update {
                it.copy(
                    genreResult = genreRepository.getGenresList(),
                    listOfColors = listOfColor
                )
            }
        }
    }

    private val listOfColor = listOf(
        Purple80,
        Purple40,
        PurpleGrey80,
        PurpleGrey40,
        Pink40,
        Pink80,
    )
}
