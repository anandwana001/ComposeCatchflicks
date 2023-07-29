package com.akshay.composecatchflicks.ui.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.akshay.composecatchflicks.R
import com.akshay.composecatchflicks.ui.theme.ComposeCatchflicksTheme
import com.akshay.composecatchflicks.ui.theme.backgroundColor

/**
 * Created by anandwana001 on
 * 01, November, 2022
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatchflicksTopAppBar(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.app_name),
) {
    TopAppBar(
        title = {
            Text(
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                text = title,
                fontSize = 22.sp,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor
        ),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarPreview() {
    ComposeCatchflicksTheme {
        CatchflicksTopAppBar()
    }
}
