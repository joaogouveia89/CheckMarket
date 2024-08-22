package io.github.joaogouveia89.checkmarket.marketList.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    onItemClick: (Int) -> Unit
) {

    Column(
        modifier = modifier
            .padding(paddingValues)
    ) {
        LazyColumn(
            modifier = modifier
        ) {
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
                        marketItem = categoryItems[index]
                    )
                }
            }
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
        marketItemsList = listGroupped,
        paddingValues = PaddingValues(16.dp),
        onItemClick = {}
    )
}