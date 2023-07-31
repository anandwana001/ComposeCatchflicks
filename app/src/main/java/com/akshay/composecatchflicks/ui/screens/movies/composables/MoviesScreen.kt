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
    popularMovies: LazyPagingItems<Movie>,
    nowPlaying: LazyPagingItems<Movie>,
    upcoming: LazyPagingItems<Movie>,
    popTo: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .background(screenBackgroundColor)
            .fillMaxHeight()
    ) {
        showUpcomingMovies(upcoming, popTo)
        showNowPlayingMovies(nowPlaying, popTo)
        showPopularMovies(popularMovies, popTo)
    }
}

private fun LazyListScope.showPopularMovies(
    popularMovies: LazyPagingItems<Movie>,
    popTo: (Int) -> Unit,
) {
    when (popularMovies.loadState.refresh) {
        LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.popular_movies)
                )
            }
        }

        else -> {
            item {
                ListTitle(titleId = R.string.popular_movies)
            }
            items(items = popularMovies.itemSnapshotList) { item ->
                item?.let {
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
    }
}

private fun LazyListScope.showUpcomingMovies(
    upcomingMovies: LazyPagingItems<Movie>,
    popTo: (Int) -> Unit,
) {
    when (upcomingMovies.loadState.refresh) {
        LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.upcoming_movies)
                )
            }
        }

        else -> {
            item {
                ListTitle(titleId = R.string.upcoming_movies)
                LazyRow {
                    items(items = upcomingMovies.itemSnapshotList) { item ->
                        item?.let {
                            TitleCard(
                                modifier = Modifier.navigate(it.id, popTo),
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
    nowPlayingMovies: LazyPagingItems<Movie>,
    popTo: (Int) -> Unit,
) {
    when (nowPlayingMovies.loadState.refresh) {
        LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.now_playing_movies)
                )
            }
        }

        else -> {
            item {
                ListTitle(titleId = R.string.now_playing_movies)
                LazyRow {
                    items(items = nowPlayingMovies.itemSnapshotList) { item ->
                        item?.let {
                            TitleCard(
                                modifier = Modifier.navigate(it.id, popTo),
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
