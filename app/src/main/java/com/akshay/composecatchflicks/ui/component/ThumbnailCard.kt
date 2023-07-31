package com.akshay.composecatchflicks.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.akshay.composecatchflicks.R
import com.akshay.composecatchflicks.ui.theme.Purple80
import com.akshay.composecatchflicks.ui.util.BASE_IMAGE_PATH

/**
 * Created by anandwana001 on
 * 29, July, 2023
 **/
@Composable
fun ThumbnailCard(modifier: Modifier = Modifier, posterThumbnail: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("$BASE_IMAGE_PATH$posterThumbnail")
            .diskCachePolicy(CachePolicy.ENABLED)
            .size(200, 380)
            .build()
    )
    val painterRem by remember(posterThumbnail) {
        mutableStateOf(painter)
    }
    when (painterRem.state) {
        is AsyncImagePainter.State.Success -> {
            Card(
                modifier = modifier
                    .padding(start = 16.dp, top = 16.dp, end = 8.dp, bottom = 24.dp)
                    .width(120.dp)
                    .height(200.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Image(
                    painter = painterRem,
                    contentDescription = "",
                    modifier = modifier.fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        is AsyncImagePainter.State.Error -> {
            Box(
                modifier = modifier
                    .padding(start = 16.dp, top = 16.dp, end = 8.dp, bottom = 24.dp)
                    .width(120.dp)
                    .height(200.dp),
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.baseline_movie_24),
                    contentDescription = null,
                )
            }
        }

        is AsyncImagePainter.State.Loading -> {
            Box(
                modifier = modifier
                    .padding(start = 16.dp, top = 16.dp, end = 8.dp, bottom = 24.dp)
                    .width(120.dp)
                    .height(200.dp),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Purple80
                )
            }
        }

        else -> Unit
    }
}