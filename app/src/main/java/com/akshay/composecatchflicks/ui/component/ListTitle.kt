package com.akshay.composecatchflicks.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by anandwana001 on
 * 03, April, 2023
 **/
@Composable
fun ListTitle(@StringRes titleId: Int) {
    val titleRemember by remember {
        mutableIntStateOf(titleId)
    }
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        style = MaterialTheme.typography.headlineSmall,
        text = stringResource(id = titleRemember),
        textDecoration = TextDecoration.Underline,
        maxLines = 1,
        color = Color.Black,
        fontSize = 16.sp,
    )
}