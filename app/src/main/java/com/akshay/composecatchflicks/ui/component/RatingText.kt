package com.akshay.composecatchflicks.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akshay.composecatchflicks.ui.theme.textColor

/**
 * Created by anandwana001 on
 * 29, July, 2023
 **/
@Composable
fun RatingText(modifier: Modifier = Modifier, rating: Float) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 8.dp, top = 8.dp),
        text = rating.toString(),
        textAlign = TextAlign.End,
        fontSize = 14.sp,
        maxLines = 1,
        style = MaterialTheme.typography.headlineSmall,
        color = textColor,
    )
}