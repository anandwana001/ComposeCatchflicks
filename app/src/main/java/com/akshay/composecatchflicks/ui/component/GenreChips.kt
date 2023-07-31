package com.akshay.composecatchflicks.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akshay.composecatchflicks.domain.model.Genres

/**
 * Created by anandwana001 on
 * 01, August, 2023
 **/
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenreChips(genres: List<Genres>) {
    FlowRow(
        Modifier
            .fillMaxWidth(1f)
            .wrapContentHeight(align = Alignment.Top),
        horizontalArrangement = Arrangement.Start,
    ) {
        repeat(genres.size) { index ->
            genres[index].name?.let {
                AssistChip(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .align(alignment = Alignment.CenterVertically),
                    onClick = { },
                    label = {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = it
                        )
                    })
            }
        }
    }
}