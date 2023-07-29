package com.akshay.composecatchflicks.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.akshay.composecatchflicks.R
import com.akshay.composecatchflicks.ui.theme.Purple40

/**
 * Created by anandwana001 on
 * 29, July, 2023
 **/

@Composable
fun ShowLoading(modifier: Modifier, text: String = stringResource(id = R.string.loading)) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = text)
        Spacer(modifier = Modifier.height(4.dp))
        CircularProgressIndicator(color = Purple40)
    }
}