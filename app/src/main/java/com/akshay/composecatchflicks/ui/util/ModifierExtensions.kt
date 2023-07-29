package com.akshay.composecatchflicks.ui.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by anandwana001 on
 * 29, July, 2023
 **/

fun Modifier.navigate(id: Int?, popTo: (Int) -> Unit) = clickable {
    id?.let { popTo(it) }
}

fun Modifier.movieCardWidth(type: MovieCardType) =
    if (type == MovieCardType.FULL) this.fillMaxWidth() else this.width(300.dp)
