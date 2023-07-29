package com.akshay.composecatchflicks.ui.screens.search.data

import androidx.compose.ui.graphics.Color
import com.akshay.composecatchflicks.domain.model.Genres

/**
 * Created by anandwana001 on
 * 01, July, 2023
 **/
data class SearchState(
    val genreResult: List<Genres> = emptyList(),
    val searchTextField: String = "",
    val listOfColors: List<Color> = emptyList(),
    val searchResult: List<String> = emptyList(),
)
