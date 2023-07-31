package com.akshay.composecatchflicks.ui.screens.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.akshay.composecatchflicks.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 14, November, 2022
 **/
@HiltViewModel
class MoviesViewModel @Inject constructor(
    moviesRepository: MoviesRepository
) : ViewModel() {

    val popularMoviesList = moviesRepository.getPopularMovies("en").cachedIn(viewModelScope)
    val nowPlayingMoviesList = moviesRepository.getNowPlayingMovies("en").cachedIn(viewModelScope)
    val upcomingMoviesList = moviesRepository.getUpcomingMovies("en").cachedIn(viewModelScope)
}
