package io.github.joaogouveia89.checkmarket.core.presentation.components.CheckMarketAppBar

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import io.github.joaogouveia89.checkmarket.ui.theme.CheckMarketRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckMarketAppBar(
    @StringRes title: Int,
    textColor: Color = Color.White,
    backgroundColor: Color = CheckMarketRed
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = title),
                color = textColor
            )
        },
        colors = topAppBarColors(
            containerColor = backgroundColor
        )
    )
}