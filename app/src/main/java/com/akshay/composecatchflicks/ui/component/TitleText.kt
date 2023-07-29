package com.akshay.composecatchflicks.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by anandwana001 on
 * 29, July, 2023
 **/
@Composable
fun TitleText(modifier: Modifier, title: String) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        text = title,
        maxLines = 2,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        color = Color.Black,
        style = MaterialTheme.typography.headlineLarge
    )
}