package io.github.joaogouveia89.checkmarket.core.presentation.topBars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import io.github.joaogouveia89.checkmarket.ui.theme.CheckMarketRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun checkMarketTopBarColors(): TopAppBarColors {
    return topAppBarColors(
        containerColor = CheckMarketRed,
        actionIconContentColor = White,
        navigationIconContentColor = White,
        titleContentColor = White,
        scrolledContainerColor = CheckMarketRed
    )
}