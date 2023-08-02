package com.akshay.composecatchflicks.ui.screens.tv.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.akshay.composecatchflicks.R
import com.akshay.composecatchflicks.domain.model.Tv
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
    topRatedTv: LazyPagingItems<Tv>,
    popTo: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .background(screenBackgroundColor)
            .fillMaxHeight()
    ) {
        showTopRatedTv(topRatedTv, popTo)
    }
}

private fun LazyListScope.showTopRatedTv(
    topRatedTv: LazyPagingItems<Tv>,
    popTo: (Int) -> Unit,
) {

    item {
        Spacer(modifier = Modifier.height(20.dp))
    }

    items(topRatedTv.itemCount) { index ->
        topRatedTv[index].let {
            it?.let {
                TitleCard(
                    modifier = Modifier.navigate(it.id, popTo),
                    title = it.name,
                    voteAverage = it.voteAverage,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    cardType = MovieCardType.FULL
                )
            }

        }
    }

    when {
        topRatedTv.loadState.refresh is LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.top_rated_tv)
                )
            }
        }

        topRatedTv.loadState.append is LoadState.Loading -> {
            item {
                ShowLoading(
                    text = stringResource(id = R.string.top_rated_tv)
                )
            }
        }
    }
}
