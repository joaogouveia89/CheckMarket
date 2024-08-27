package io.github.joaogouveia89.checkmarket.marketList.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.core.presentation.topBars.CheckMarketAppBar
import io.github.joaogouveia89.checkmarket.marketList.presentation.components.MarketListContent
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

// TODO: Add a FAB to redirects to add market item screen
@Composable
fun MarketListScreen(
    onNavigateToAddMarketItemClick: () -> Unit
) {

    Scaffold(
        topBar = {
            CheckMarketAppBar(
                title = R.string.market_list_title
            )
        }
    ) { paddingValues ->

        val marketItemsList = listOf(
            // SNACKS
            MarketItem(
                id = 16,
                name = "Chips",
                category = MarketItemCategory.SNACKS,
                price = 1.5,
                createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            ),
            MarketItem(
                id = 17,
                name = "Chocolate",
                category = MarketItemCategory.SNACKS,
                price = 2.0,
                createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            ),
            MarketItem(
                id = 18,
                name = "Biscoito",
                category = MarketItemCategory.SNACKS,
                price = 1.0,
                createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            ),
            // CLEANING
            MarketItem(
                id = 31,
                name = "Desinfetante",
                category = MarketItemCategory.CLEANING,
                price = 2.0,
                createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            ),
            MarketItem(
                id = 32,
                name = "Sabão em Pó",
                category = MarketItemCategory.CLEANING,
                price = 3.0,
                createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            ),
            MarketItem(
                id = 33,
                name = "Água Sanitária",
                category = MarketItemCategory.CLEANING,
                price = 1.0,
                createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            ),
            // HOUSEHOLD
            MarketItem(
                id = 34,
                name = "Lâmpada",
                category = MarketItemCategory.HOUSEHOLD,
                price = 5.0,
                createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            ),
            MarketItem(
                id = 35,
                name = "Pilhas",
                category = MarketItemCategory.HOUSEHOLD,
                price = 3.0,
                createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            ),
            MarketItem(
                id = 36,
                name = "Fita Adesiva",
                category = MarketItemCategory.HOUSEHOLD,
                price = 2.0,
                createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            )
        )

        val listGroupped = marketItemsList.groupBy { it.category }

        MarketListContent(
            modifier = Modifier
                .fillMaxHeight(),
            marketItemsList = listGroupped,
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
        onNavigateToAddMarketItemClick = {}
    )
}