package com.akshay.composecatchflicks.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.akshay.composecatchflicks.ui.screens.moviedetail.compose.MovieDetailScreen
import com.akshay.composecatchflicks.ui.screens.movies.composables.MoviesScreen
import com.akshay.composecatchflicks.ui.screens.movies.viewmodel.MoviesViewModel
import com.akshay.composecatchflicks.ui.screens.search.composable.GenreDetailScreen
import com.akshay.composecatchflicks.ui.screens.search.composable.SearchScreen
import com.akshay.composecatchflicks.ui.screens.search.viewModel.GenreDetailViewModel
import com.akshay.composecatchflicks.ui.screens.search.viewModel.SearchViewModel
import com.akshay.composecatchflicks.ui.screens.tv.composable.TvScreen
import com.akshay.composecatchflicks.ui.screens.tv.viewModel.TvViewModel
import com.akshay.composecatchflicks.ui.screens.tvdetail.compose.TvDetailScreen
import com.akshay.composecatchflicks.ui.screens.tvdetail.viewModel.TvDetailViewModel
import kotlinx.coroutines.launch

/**
 * Created by anandwana001 on
 * 08, November, 2022
 **/

const val MOVIE_DETAIL_ROUTE = "detail/{movieId}"
const val TV_DETAIL_ROUTE = "tvDetail/{seriesId}"
const val GENRE_DETAIL_ROUTE = "genreDetail/{genreId}/{genreName}"

@Composable
fun NavHostContainer(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    val viewModel = hiltViewModel<MoviesViewModel>()
    val searchViewModel = hiltViewModel<SearchViewModel>()
    val tvViewModel = hiltViewModel<TvViewModel>()
    NavHost(
        navController = navController,
        startDestination = ComposeCatchflicksCategory.MOVIE.route,
        modifier = Modifier.padding(paddingValues),
        builder = {
            composable(ComposeCatchflicksCategory.MOVIE.route) {
                val popularMovies = viewModel.popularMoviesList.collectAsLazyPagingItems()
                val nowPlaying = viewModel.nowPlayingMoviesList.collectAsLazyPagingItems()
                val upcoming = viewModel.upcomingMoviesList.collectAsLazyPagingItems()
                MoviesScreen(
                    popularMovies = popularMovies,
                    nowPlaying = nowPlaying,
                    upcoming = upcoming,
                ) { id ->
                    navController.navigate("detail/${id}") {
                        popUpTo("movies")
                    }
                }
            }
            composable(ComposeCatchflicksCategory.TV.route) {
                val topRatedTv = tvViewModel.topRatedTvList.collectAsLazyPagingItems()
                TvScreen(topRatedTv = topRatedTv) { id ->
                    navController.navigate("tvDetail/${id}") {
                        popUpTo("tv")
                    }
                }
            }
            composable(ComposeCatchflicksCategory.SEARCH.route) {
                val coroutineScope = rememberCoroutineScope()
                val searchState by searchViewModel.searchStateData.collectAsStateWithLifecycle()
                SearchScreen(searchState = searchState, popTo = { id, name ->
                    navController.navigate("genreDetail/${id}/${name}") {
                        popUpTo("search")
                    }
                }, searchEvent = {
                    coroutineScope.launch {
                        searchViewModel.emitEvent(it)
                    }
                })
            }
            composable(
                MOVIE_DETAIL_ROUTE,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down)
                },
                popExitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up)
                }
            ) {
                MovieDetailScreen()
            }

            composable(
                TV_DETAIL_ROUTE,
                arguments = listOf(navArgument("seriesId") { type = NavType.IntType })
            ) {
                val tvDetailViewModel = hiltViewModel<TvDetailViewModel>()
                val detail = tvDetailViewModel.tvStateData.collectAsStateWithLifecycle()
                TvDetailScreen(detail = detail)
            }

            composable(
                GENRE_DETAIL_ROUTE,
                arguments = listOf(navArgument("genreId") { type = NavType.IntType },
                    navArgument("genreName") { type = NavType.StringType }),
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
                },
                popExitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)
                }
            ) {
                val genreViewModel = hiltViewModel<GenreDetailViewModel>()
                val list = genreViewModel.genreDetailList.collectAsLazyPagingItems()
                GenreDetailScreen(
                    genreDetailList = list,
                    genreName = genreViewModel.genreName
                ) { id ->
                    navController.navigate("detail/${id}")
                }
            }
        })
}