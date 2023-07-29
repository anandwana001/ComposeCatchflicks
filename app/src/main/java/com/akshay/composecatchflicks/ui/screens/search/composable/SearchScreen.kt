package com.akshay.composecatchflicks.ui.screens.search.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.akshay.composecatchflicks.R
import com.akshay.composecatchflicks.domain.model.Genres
import com.akshay.composecatchflicks.ui.component.ListTitle
import com.akshay.composecatchflicks.ui.screens.search.data.SearchEvent
import com.akshay.composecatchflicks.ui.screens.search.data.SearchState
import com.akshay.composecatchflicks.ui.theme.Purple40
import com.akshay.composecatchflicks.ui.theme.backgroundColor
import com.akshay.composecatchflicks.ui.theme.screenBackgroundColor

/**
 * Created by anandwana001 on
 * 08, November, 2022
 **/
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchState: SearchState,
    searchEvent: (SearchEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(screenBackgroundColor)
    ) {
        SearchField(uiState = searchState, searchEvent = searchEvent)
        SearchResultUi(uiState = searchState)
        ListTitle(R.string.genre)
        GenreList(list = searchState.genreResult, colors = searchState.listOfColors)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchField(
    modifier: Modifier = Modifier,
    uiState: SearchState,
    searchEvent: (SearchEvent) -> Unit
) {
    TextField(
        value = uiState.searchTextField,
        onValueChange = {
            searchEvent(SearchEvent.SearchQuery(it))
        },
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .fillMaxWidth(),
        trailingIcon = {
            if (uiState.searchResult.isNotEmpty()) {
                Icon(
                    Icons.Rounded.Clear,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        searchEvent(SearchEvent.SearchClear)
                    }
                )
            } else {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = ""
                )
            }
        },
        placeholder = {
            Text(text = "look for movies here", color = screenBackgroundColor)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            cursorColor = Purple40,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedTrailingIconColor = screenBackgroundColor,
            focusedTrailingIconColor = Purple40,
        ),
        shape = if (uiState.searchResult.isNotEmpty()) {
            RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)
        } else {
            RoundedCornerShape(8.dp)
        }
    )
}

@Composable
private fun SearchResultUi(
    modifier: Modifier = Modifier,
    uiState: SearchState,
) {
    if (uiState.searchResult.isNotEmpty()) {
        LazyColumn(
            modifier = modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                )
        ) {
            itemsIndexed(uiState.searchResult, key = { index, item -> index }) { index, item ->
                Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)) {
                    Text(text = item)
                    Divider(color = backgroundColor)
                }
            }
        }
    }
}

@Composable
private fun GenreList(list: List<Genres>, colors: List<Color>) {
    val listRem by rememberSaveable {
        mutableStateOf(list)
    }
    val listOfColors by rememberSaveable {
        mutableStateOf(colors)
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
    ) {
        itemsIndexed(items = listRem, key = { index, item ->
            item
        }) { index, item ->
            item.name?.let {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .background(
                            color = listOfColors[index % listOfColors.size],
                            shape = RoundedCornerShape(8.dp)
                        )
                        .size(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = it, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
