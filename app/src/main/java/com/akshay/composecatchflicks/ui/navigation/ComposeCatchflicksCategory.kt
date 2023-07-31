package com.akshay.composecatchflicks.ui.navigation

import androidx.annotation.StringRes
import com.akshay.composecatchflicks.R

/**
 * Created by anandwana001 on
 * 01, November, 2022
 **/
enum class ComposeCatchflicksCategory(
    @StringRes val titleId: Int,
    val route: String
) {
    MOVIE(R.string.movies, "movies"),
    TV(R.string.tv, "tv"),
    SEARCH(R.string.search, "search"),
}
