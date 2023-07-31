package com.akshay.composecatchflicks.ui.screens.moviedetail.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.akshay.composecatchflicks.domain.model.MovieDetail
import com.akshay.composecatchflicks.ui.component.GenreChips
import com.akshay.composecatchflicks.ui.util.BASE_IMAGE_PATH

/**
 * Created by anandwana001 on
 * 15, December, 2022
 **/
@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    detail: State<MovieDetail>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)
    ) {
        item {
            Image(
                painter = rememberAsyncImagePainter(BASE_IMAGE_PATH + detail.value.backdropPath),
                contentDescription = "",
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
        }

        item {
            Row(modifier = Modifier.height(50.dp)) {
                Text(
                    modifier = modifier.padding(16.dp),
                    text = detail.value.title ?: ""
                )
                VerticalDivider(modifier = Modifier.padding(vertical = 16.dp), color = Color.Black)
                Text(
                    modifier = modifier.padding(16.dp),
                    text = detail.value.voteAverage.toString()
                )
            }
        }

        item {
            Text(
                modifier = modifier.padding(16.dp),
                text = "Story Line"
            )
            detail.value.overview?.let {
                Text(
                    modifier = modifier.padding(16.dp),
                    text = it
                )
            }
        }

        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "Genres"
                )
                detail.value.genres.takeIf { it.isNotEmpty() }?.let {
                    GenreChips(it)
                }
            }
        }
    }
}
