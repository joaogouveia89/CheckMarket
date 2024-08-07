package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.presentation.topBars.CheckMarketAppBar
import io.github.joaogouveia89.checkmarket.core.presentation.topBars.CheckMarketSearchAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MarketListiTemAddScreen(
    onNavigateBack: () -> Unit
) {
    // TODO: ADD A BACK FUNCTIONALITY TO THE APPBAR
    Scaffold(
        topBar = {
            // Top App Bar
            CheckMarketSearchAppBar(
                onBackClick = { onNavigateBack() },
                onQueryChange = {/* TODO Call view model */}
            )
        }
    ) { paddingValues ->
//        MarketListItemAddContent(
//            modifier = Modifier
//                .fillMaxHeight(),
//            paddingValues = paddingValues,
//            onNavigateBack = onNavigateBack
//        )
    }
}

@Preview
@Composable
private fun MarketListItemAddScreenPreview() {
    MarketListiTemAddScreen{}
}

