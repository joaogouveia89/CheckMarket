package io.github.joaogouveia89.checkmarket.marketList.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun MarketListContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    marketItemsList: Map<MarketItemCategory, List<MarketItem>>,
    isLoading: Boolean,
    onItemClick: (MarketItemCategory, itemIndex: Int) -> Unit,
    onNavigateToAddMarketItemClick: () -> Unit
) {

    Box(
        modifier = modifier
            .padding(paddingValues)
    ) {
        if(isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }else{
            LazyColumn {
                marketItemsList.keys.forEach { category ->
                    item {
                        CategoryMark(
                            modifier = Modifier.padding(vertical = 12.dp),
                            title = category.nameRes,
                            icon = category.icon
                        )
                    }
                    val categoryItems = marketItemsList[category]
                    items(categoryItems!!.size) { index ->
                        MarketListItem(
                            modifier = Modifier
                                .clickable { onItemClick(category, index) },
                            marketItem = categoryItems[index]
                        )
                    }
                }
            }
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = { onNavigateToAddMarketItemClick() },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.add_market_item_descr),
                        tint = Color.White
                    )
                },
                text = {
                    Text(
                        text = stringResource(id = R.string.add_market_item),
                        color = Color.White
                    )
                },
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MarketListContentPreview() {
    val marketItemsList = listOf(
        // SNACKS
        MarketItem(
            id = 16,
            name = "Chips",
            category = MarketItemCategory.SNACKS,
            price = 1.5,
            quantity = "100g",
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        ),
        MarketItem(
            id = 17,
            name = "Chocolate",
            category = MarketItemCategory.SNACKS,
            price = 2.0,
            quantity = "100g",
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        ),
        MarketItem(
            id = 18,
            name = "Biscoito",
            category = MarketItemCategory.SNACKS,
            price = 1.0,
            quantity = "100g",
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        ),
        // CLEANING
        MarketItem(
            id = 31,
            name = "Desinfetante",
            category = MarketItemCategory.CLEANING,
            price = 2.0,
            quantity = "1L",
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        ),
        MarketItem(
            id = 32,
            name = "Sabão em Pó",
            category = MarketItemCategory.CLEANING,
            price = 3.0,
            quantity = "1kg",
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        ),
        MarketItem(
            id = 33,
            name = "Água Sanitária",
            category = MarketItemCategory.CLEANING,
            price = 1.0,
            quantity = "1L",
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        ),
        // HOUSEHOLD
        MarketItem(
            id = 34,
            name = "Lâmpada",
            category = MarketItemCategory.HOUSEHOLD,
            price = 5.0,
            quantity = "100g",
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        ),
        MarketItem(
            id = 35,
            name = "Pilhas",
            category = MarketItemCategory.HOUSEHOLD,
            price = 3.0,
            quantity = "100g",
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        ),
        MarketItem(
            id = 36,
            name = "Fita Adesiva",
            category = MarketItemCategory.HOUSEHOLD,
            price = 2.0,
            quantity = "100g",
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now()
        )
    )

    val listGroupped = marketItemsList.groupBy { it.category }

    MarketListContent(
        marketItemsList = listGroupped,
        isLoading = false,
        paddingValues = PaddingValues(16.dp),
        onItemClick = {_, _ -> },
        onNavigateToAddMarketItemClick = {}
    )
}

@Preview
@Composable
private fun MarketListContentLoadingPreview() {
    MarketListContent(
        marketItemsList = mapOf(),
        isLoading = true,
        paddingValues = PaddingValues(16.dp),
        onItemClick = {_, _ -> },
        onNavigateToAddMarketItemClick = {}
    )
}