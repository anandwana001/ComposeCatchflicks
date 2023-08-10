package com.akshay.composecatchflicks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akshay.composecatchflicks.ui.component.CatchflicksBottomNavigationBar
import com.akshay.composecatchflicks.ui.component.CatchflicksTopAppBar
import com.akshay.composecatchflicks.ui.navigation.GENRE_DETAIL_ROUTE
import com.akshay.composecatchflicks.ui.navigation.MOVIE_DETAIL_ROUTE
import com.akshay.composecatchflicks.ui.navigation.NavHostContainer
import com.akshay.composecatchflicks.ui.navigation.TV_DETAIL_ROUTE
import com.akshay.composecatchflicks.ui.theme.ComposeCatchflicksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCatchflicksTheme {
                val navController = rememberNavController()
                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                when (navBackStackEntry?.destination?.route) {
                    MOVIE_DETAIL_ROUTE, TV_DETAIL_ROUTE, GENRE_DETAIL_ROUTE -> bottomBarState.value =
                        false
                    else -> {
                        if (!bottomBarState.value) bottomBarState.value = true
                    }
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { CatchflicksTopAppBar() },
                    bottomBar = {
                        if (bottomBarState.value) {
                            CatchflicksBottomNavigationBar(
                                navController = navController,
                            )
                        }
                    }
                ) { paddingValues ->
                    NavHostContainer(navController = navController, paddingValues = paddingValues)
                }
            }
        }
    }
}

/**
 * TODO
 * 1. Load more pages on home screen
 * 2. Smooth scroll
 * 3. Movie Detail Screen UI
 * 4. Back button on detail screen
 * 5. Hide bottom bar on detail screen
 * 6. Horizontal multiple list in TV tab
 * 7. Search
 * 8. UI Test
 * 9. Unit Test
 */
