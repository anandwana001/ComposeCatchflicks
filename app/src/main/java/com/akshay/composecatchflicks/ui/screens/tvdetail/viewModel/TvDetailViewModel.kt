package com.akshay.composecatchflicks.ui.screens.tvdetail.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.composecatchflicks.domain.model.TvDetail
import com.akshay.composecatchflicks.domain.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 31, July, 2023
 **/
@HiltViewModel
class TvDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tvRepository: TvRepository
) : ViewModel() {

    private val _tvStateData = MutableStateFlow(TvDetail())
    val tvStateData = _tvStateData.asStateFlow()

    private val seriesId: Int = checkNotNull(savedStateHandle["seriesId"])

    init {
        getTvDetail()
    }

    private fun getTvDetail() {
        viewModelScope.launch {
            _tvStateData.value = tvRepository.getTvDetails(seriesId)
        }
    }
}