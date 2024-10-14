package io.github.joaogouveia89.checkmarket.marketList.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.core.presentation.topBars.CheckMarketAppBar
import io.github.joaogouveia89.checkmarket.marketList.presentation.components.MarketListContent
import io.github.joaogouveia89.checkmarket.marketList.presentation.state.MarketListUiState
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

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
                .fillMaxHeight(),
            marketItemsList = uiState.items,
            paddingValues = paddingValues,
            onItemClick = { itemIndex ->

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