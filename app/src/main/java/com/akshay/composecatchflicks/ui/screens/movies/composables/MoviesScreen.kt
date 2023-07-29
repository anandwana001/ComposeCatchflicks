package com.akshay.composecatchflicks.ui.screens.movies.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
 * 08, November, 2022
 **/
@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    data: LazyPagingItems<Movie>,
    nowPlaying: LazyPagingItems<Movie>,
    upcoming: LazyPagingItems<Movie>,
    popTo: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .background(screenBackgroundColor)
            .fillMaxHeight()
    ) {
        showUpcomingMovies(upcoming, modifier, popTo)
        showNowPlayingMovies(nowPlaying, modifier, popTo)
        showPopularMovies(data, modifier, popTo)
    }
}

private fun LazyListScope.showPopularMovies(
    data: LazyPagingItems<Movie>,
    modifier: Modifier,
    popTo: (Int) -> Unit,
) {
    when (data.loadState.refresh) {
        LoadState.Loading -> {
            item {
                ShowLoading(
                    modifier = modifier,
                    text = stringResource(id = R.string.popular_movies)
                )
            }
        }

        else -> {
            item {
                ListTitle(R.string.popular_movies)
            }
            items(items = data.itemSnapshotList) { item ->
                item?.let {
                    TitleCard(
                        modifier = modifier.navigate(it.id, popTo),
                        title = it.title,
                        voteAverage = it.voteAverage,
                        overview = it.overview,
                        posterPath = it.posterPath,
                        cardType = MovieCardType.FULL
                    )
                }
            }
        }
    }
}

private fun LazyListScope.showUpcomingMovies(
    nowPlaying: LazyPagingItems<Movie>,
    modifier: Modifier,
    popTo: (Int) -> Unit,
) {
    when (nowPlaying.loadState.refresh) {
        LoadState.Loading -> {
            item {
                ShowLoading(
                    modifier = modifier,
                    text = stringResource(id = R.string.upcoming_movies)
                )
            }
        }

        else -> {
            item {
                ListTitle(R.string.now_playing_movies)
                LazyRow {
                    items(items = nowPlaying.itemSnapshotList) { item ->
                        item?.let {
                            TitleCard(
                                modifier = modifier.navigate(it.id, popTo),
                                title = it.title,
                                voteAverage = it.voteAverage,
                                overview = it.overview,
                                posterPath = it.posterPath,
                                cardType = MovieCardType.HALF
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun LazyListScope.showNowPlayingMovies(
    upcoming: LazyPagingItems<Movie>,
    modifier: Modifier,
    popTo: (Int) -> Unit,
) {
    when (upcoming.loadState.refresh) {
        LoadState.Loading -> {
            item {
                ShowLoading(
                    modifier = modifier,
                    text = stringResource(id = R.string.now_playing_movies)
                )
            }
        }

        else -> {
            item {
                ListTitle(R.string.upcoming_movies)
                LazyRow {
                    items(items = upcoming.itemSnapshotList) { item ->
                        item?.let {
                            TitleCard(
                                modifier = modifier.navigate(it.id, popTo),
                                title = it.title,
                                voteAverage = it.voteAverage,
                                overview = it.overview,
                                posterPath = it.posterPath,
                                cardType = MovieCardType.HALF
                            )
                        }
                    }
                }
            }
        }
    }
}
