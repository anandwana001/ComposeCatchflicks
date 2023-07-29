package com.akshay.composecatchflicks.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akshay.composecatchflicks.R
import com.akshay.composecatchflicks.ui.navigation.ComposeCatchflicksCategory
import com.akshay.composecatchflicks.ui.theme.ComposeCatchflicksTheme
import com.akshay.composecatchflicks.ui.theme.UnSelectedTextItemColor
import com.akshay.composecatchflicks.ui.theme.backgroundColor

/**
 * Created by anandwana001 on
 * 01, November, 2022
 **/
@Composable
fun CatchflicksBottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    itemList: List<ComposeCatchflicksCategory> = ComposeCatchflicksCategory.values().asList(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    CatchflicksBottomBar(
        modifier = modifier,
    ) {
        itemList.forEach { category ->
            CatchflicksBottomNavigationBarItem(
                selected = currentRoute == category.route,
                label = {
                    Text(
                        style = MaterialTheme.typography.bodySmall,
                        text = stringResource(category.titleId)
                    )
                },
                onClick = {
                    navController.navigate(category.route)
                },
            )
        }
    }
}

@Composable
fun CatchflicksBottomBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        containerColor = backgroundColor,
        modifier = modifier,
        content = content
    )
}

@Composable
fun RowScope.CatchflicksBottomNavigationBarItem(
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = selected,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = Color.White,
            unselectedTextColor = UnSelectedTextItemColor,
            unselectedIconColor = UnSelectedTextItemColor
        ),
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.popular),
                contentDescription = "",
                modifier = Modifier.height(20.dp),
            )
        },
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
private fun CatchflicksBottomNavigationBarPreview() {
    ComposeCatchflicksTheme {
        CatchflicksBottomNavigationBar(
            navController = rememberNavController(),
        )
    }
}