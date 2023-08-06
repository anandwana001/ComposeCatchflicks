package com.akshay.composecatchflicks.ui.screens.moviedetail.compose

import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.akshay.composecatchflicks.ui.component.GenreChips
import com.akshay.composecatchflicks.ui.screens.moviedetail.viewModel.MovieDetailViewModel
import com.akshay.composecatchflicks.ui.theme.screenBackgroundColor
import com.akshay.composecatchflicks.ui.util.BASE_IMAGE_PATH
import com.akshay.composecatchflicks.ui.util.convertImageUrlToBitmap
import com.akshay.composecatchflicks.ui.util.extractColorsFromBitmap

/**
 * Created by anandwana001 on
 * 15, December, 2022
 **/
@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val detail = viewModel.movieStateData.collectAsStateWithLifecycle().value

    val context = LocalContext.current
    var backgroundColor by remember { mutableStateOf(screenBackgroundColor) }

    val imageUrl = rememberSaveable(detail.backdropPath) {
        mutableStateOf(detail.backdropPath)
    }

    LaunchedEffect(imageUrl) {
        imageUrl.value?.let {
            val bitmap = convertImageUrlToBitmap(
                imageUrl = BASE_IMAGE_PATH + it,
                context = context
            )
            if (bitmap != null) {
                val vibrantColor = extractColorsFromBitmap(
                    bitmap = bitmap
                )["vibrant"] ?: screenBackgroundColor.toString()
                backgroundColor = Color(parseColor(vibrantColor))
            }
        }
    }


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(backgroundColor.value))
            .padding(bottom = 20.dp)
    ) {
        item {
            Image(
                painter = rememberAsyncImagePainter(BASE_IMAGE_PATH + detail.backdropPath),
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
                    text = detail.title ?: ""
                )
                VerticalDivider(modifier = Modifier.padding(vertical = 16.dp), color = Color.Black)
                Text(
                    modifier = modifier.padding(16.dp),
                    text = detail.voteAverage.toString()
                )
            }
        }

        item {
            Text(
                modifier = modifier.padding(16.dp),
                text = "Story Line"
            )
            detail.overview?.let {
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
                detail.genres.takeIf { it.isNotEmpty() }?.let {
                    GenreChips(it)
                }
            }
        }
    }
}
