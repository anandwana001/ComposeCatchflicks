package com.akshay.composecatchflicks.ui.screens.tv.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.akshay.composecatchflicks.R
import com.akshay.composecatchflicks.domain.model.Tv
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
fun TvScreen(
    modifier: Modifier = Modifier,
    data: LazyPagingItems<Tv>,
    popTo: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .background(screenBackgroundColor)
            .fillMaxHeight()
    ) {
        showTopRatedTv(data, modifier, popTo)
    }
}

private fun LazyListScope.showTopRatedTv(
    data: LazyPagingItems<Tv>,
    modifier: Modifier,
    popTo: (Int) -> Unit,
) {
    when (data.loadState.refresh) {
        LoadState.Loading -> {
            item {
                ShowLoading(
                    modifier = modifier,
                    text = stringResource(id = R.string.top_rated_tv)
                )
            }
        }

        else -> {
            item {
                ListTitle(R.string.top_rated_tv)
            }
            items(items = data.itemSnapshotList) { item ->
                item?.let {
                    TitleCard(
                        modifier = modifier.navigate(it.id, popTo),
                        title = it.name,
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
