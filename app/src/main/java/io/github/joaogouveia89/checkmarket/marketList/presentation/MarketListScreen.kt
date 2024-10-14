package io.github.joaogouveia89.checkmarket.marketList.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.presentation.topBars.CheckMarketAppBar
import io.github.joaogouveia89.checkmarket.marketList.presentation.components.MarketListContent
import io.github.joaogouveia89.checkmarket.marketList.presentation.state.MarketListUiState

// TODO: Add a FAB to redirects to add market item screen
@Composable
fun MarketListScreen(
    uiState: MarketListUiState,
    onNavigateToAddMarketItemClick: () -> Unit
) {

    Scaffold(
        topBar = {
            CheckMarketAppBar(
                title = R.string.market_list_title
            )
        }
    ) { paddingValues ->

        MarketListContent(
            modifier = Modifier
                .fillMaxSize(),
            marketItemsList = uiState.items,
            isLoading = uiState.isLoading,
            paddingValues = paddingValues,
            onItemClick = { category, index ->
                println("Category: $category, Index: $index")
            },
            onNavigateToAddMarketItemClick = onNavigateToAddMarketItemClick
        )
    }
}

@Preview
@Composable
private fun MarketListScreenPreview() {
    MarketListScreen(
        uiState = MarketListUiState(),
        onNavigateToAddMarketItemClick = {}
    )
}