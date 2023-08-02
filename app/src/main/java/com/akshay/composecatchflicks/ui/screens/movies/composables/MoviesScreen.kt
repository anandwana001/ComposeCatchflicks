package com.akshay.composecatchflicks.ui.screens.movies.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
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
    item {
        ListTitle(titleId = R.string.popular_movies)
    }

    items(popularMovies.itemCount) { index ->
        popularMovies[index].let {
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
        popularMovies.loadState.refresh is LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.popular_movies)
                )
            }
        }

        popularMovies.loadState.append is LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.popular_movies)
                )
            }
        }
    }
}

private fun LazyListScope.showUpcomingMovies(
    upcomingMovies: LazyPagingItems<Movie>,
    popTo: (Int) -> Unit,
) {
    item {
        ListTitle(titleId = R.string.upcoming_movies)
        LazyRow {
            items(upcomingMovies.itemCount) { index ->
                upcomingMovies[index].let {
                    it?.let {
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

    item {
        Spacer(modifier = Modifier.width(20.dp))
    }


    when {
        upcomingMovies.loadState.refresh is LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.upcoming_movies)
                )
            }
        }

        upcomingMovies.loadState.append is LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.upcoming_movies)
                )
            }
        }
    }
}

private fun LazyListScope.showNowPlayingMovies(
    nowPlayingMovies: LazyPagingItems<Movie>,
    popTo: (Int) -> Unit,
) {

    item {
        ListTitle(titleId = R.string.now_playing_movies)
        LazyRow {
            items(nowPlayingMovies.itemCount) { index ->
                nowPlayingMovies[index].let {
                    it?.let {
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

    item {
        Spacer(modifier = Modifier.width(20.dp))
    }

    when {
        nowPlayingMovies.loadState.refresh is LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.now_playing_movies)
                )
            }
        }

        nowPlayingMovies.loadState.append is LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.now_playing_movies)
                )
            }
        }
    }
}
