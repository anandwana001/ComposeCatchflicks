package com.akshay.composecatchflicks.ui.screens.search.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.akshay.composecatchflicks.domain.repository.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 11, August, 2023
 **/
@HiltViewModel
class GenreDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val genreRepository: GenreRepository,
): ViewModel() {

    private val genreId: Int = checkNotNull(savedStateHandle["genreId"])
    val genreName: String = checkNotNull(savedStateHandle["genreName"])

    val genreDetailList = genreRepository.getGenreDetail(genreId).cachedIn(viewModelScope)
}
