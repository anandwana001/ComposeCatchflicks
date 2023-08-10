package com.akshay.composecatchflicks.ui.screens.search.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.akshay.composecatchflicks.R
import com.akshay.composecatchflicks.domain.model.Movie
import com.akshay.composecatchflicks.ui.component.ListTitle
import com.akshay.composecatchflicks.ui.component.ShowLoading
import com.akshay.composecatchflicks.ui.component.TitleCard
import com.akshay.composecatchflicks.ui.theme.screenBackgroundColor
import com.akshay.composecatchflicks.ui.util.MovieCardType
import com.akshay.composecatchflicks.ui.util.navigate

/**
 * Created by anandwana001 on
 * 11, August, 2023
 **/
@Composable
fun GenreDetailScreen(
    genreDetailList: LazyPagingItems<Movie>,
    genreName: String,
    popTo: (Int) -> Unit,
) {

    LazyColumn(
        modifier = Modifier
            .background(screenBackgroundColor)
            .fillMaxHeight()
    ) {
        showMovies(genreDetailList, genreName, popTo)
    }
}

private fun LazyListScope.showMovies(
    genreMovies: LazyPagingItems<Movie>,
    genreName: String,
    popTo: (Int) -> Unit,
) {

    item {
        ListTitle(title = genreName)
    }

    items(genreMovies.itemCount) { index ->
        genreMovies[index].let {
            it?.let {
                TitleCard(
                    modifier = Modifier.navigate(it.id, popTo),
                    title = it.title,
                    voteAverage = it.voteAverage,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    cardType = MovieCardType.FULL
                )
            }

        }
    }

    item {
        Spacer(modifier = Modifier.height(20.dp))
    }

    when {
        genreMovies.loadState.refresh is LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.genre_movies, genreName)
                )
            }
        }

        genreMovies.loadState.append is LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.genre_movies, genreName)
                )
            }
        }

        genreMovies.loadState.refresh is LoadState.Error -> {
            item {
                Text(text = "Not Loading")
            }
        }
    }
}
