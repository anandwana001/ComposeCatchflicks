package com.akshay.composecatchflicks.ui.screens.tv.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.akshay.composecatchflicks.domain.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 29, July, 2023
 **/
@HiltViewModel
class TvViewModel @Inject constructor(
    tvRepository: TvRepository
) : ViewModel() {

    val list = tvRepository.getTopRatedTv("en").cachedIn(viewModelScope)
}
