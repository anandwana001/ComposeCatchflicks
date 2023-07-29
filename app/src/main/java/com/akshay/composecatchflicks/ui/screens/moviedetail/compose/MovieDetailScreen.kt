package com.akshay.composecatchflicks.ui.screens.moviedetail.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
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

/**
 * Created by anandwana001 on
 * 15, December, 2022
 **/
@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    detail: State<MovieDetail>
) {
    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(bottom = 50.dp)) {
        item {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original" + detail.value.backdropPath),
                contentDescription = "",
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(1.0f),
                contentScale = ContentScale.Crop
            )
        }
        item {
            Row(modifier = Modifier.height(50.dp)){
                Text(
                    modifier = modifier.padding(16.dp),
                    text = detail.value.title ?: ""
                )
                VerticalDivider(modifier = Modifier.padding(vertical = 16.dp),color = Color.Black)
                Text(
                    modifier = modifier.padding(16.dp),
                    text = detail.value.voteAverage.toString() ?: "asdasdad"
                )
            }
        }
        item {
            Text(
                modifier = modifier.padding(16.dp),
                text = "Story Line"
            )
            Text(
                modifier = modifier.padding(16.dp),
                text = detail.value.overview ?: ""
            )
        }
        item {
            Text(
                modifier = modifier.padding(16.dp),
                text = "Genres"
            )
            detail.value.genres.takeIf { it.isNotEmpty() }?.apply {
                LazyRow(content = {
                    items(size) { index ->
                        get(index).name?.let {
                            Text(
                                modifier = modifier.padding(16.dp),
                                text = it
                            )
                        }
                    }
                })
            }
        }
    }
}
