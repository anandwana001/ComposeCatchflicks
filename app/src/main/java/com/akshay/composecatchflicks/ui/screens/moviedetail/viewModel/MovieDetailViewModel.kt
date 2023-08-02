package com.akshay.composecatchflicks.ui.screens.moviedetail.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.composecatchflicks.domain.model.MovieDetail
import com.akshay.composecatchflicks.domain.repository.MoviesRepository
import com.akshay.composecatchflicks.util.ComposeCatchflicksResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 02, January, 2023
 **/
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _movieStateData = MutableStateFlow(MovieDetail())
    val movieStateData = _movieStateData.asStateFlow()

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    init {
        getMovieDetail()
    }

    private fun getMovieDetail() {
        viewModelScope.launch {
            when (val response = moviesRepository.getMovieDetails(movieId)) {
                is ComposeCatchflicksResult.Success -> {
                    _movieStateData.value = response.data
                }

                else -> Unit // handle error
            }

        }
    }
}