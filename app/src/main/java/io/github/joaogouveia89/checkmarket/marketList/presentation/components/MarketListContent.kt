package io.github.joaogouveia89.checkmarket.marketList.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    onItemClick: (Int) -> Unit) {

    Column(
        modifier = modifier
            .padding(paddingValues)
    ){
        LazyColumn(
            modifier = modifier
        ) {
            marketItemsList.keys.forEach { category ->
                item{
                    CategoryMark(
                        modifier = Modifier.padding(vertical = 12.dp),
                        title = category.nameRes,
                        iconRes = category.iconRes
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
        // FRUITS
        MarketItem(
            id = 1,
            name = "Maçã",
            category = MarketItemCategory.FRUITS,
            price = 10.0,
            quantity = 4,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 2,
            name = "Banana",
            category = MarketItemCategory.FRUITS,
            price = 5.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 3,
            name = "Ameixa",
            category = MarketItemCategory.FRUITS,
            price = 7.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        // CEREALS
        MarketItem(
            id = 4,
            name = "Arroz",
            category = MarketItemCategory.CEREALS,
            price = 3.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 5,
            name = "Trigo",
            category = MarketItemCategory.CEREALS,
            price = 4.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 6,
            name = "Cevada",
            category = MarketItemCategory.CEREALS,
            price = 2.5,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        // MEAT
        MarketItem(
            id = 7,
            name = "Bife",
            category = MarketItemCategory.MEAT,
            price = 15.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 8,
            name = "Frango",
            category = MarketItemCategory.MEAT,
            price = 8.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 9,
            name = "Porco",
            category = MarketItemCategory.MEAT,
            price = 10.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        // VEGETABLES
        MarketItem(
            id = 10,
            name = "Cenoura",
            category = MarketItemCategory.VEGETABLES,
            price = 2.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 11,
            name = "Alface",
            category = MarketItemCategory.VEGETABLES,
            price = 1.5,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 12,
            name = "Tomate",
            category = MarketItemCategory.VEGETABLES,
            price = 3.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        // DAIRY
        MarketItem(
            id = 13,
            name = "Leite",
            category = MarketItemCategory.DAIRY,
            price = 2.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 14,
            name = "Queijo",
            category = MarketItemCategory.DAIRY,
            price = 5.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 15,
            name = "Iogurte",
            category = MarketItemCategory.DAIRY,
            price = 1.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
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
        // BREAD
        MarketItem(
            id = 19,
            name = "Pão",
            category = MarketItemCategory.BREAD,
            price = 1.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 20,
            name = "Pão Integral",
            category = MarketItemCategory.BREAD,
            price = 1.5,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 21,
            name = "Pão de Forma",
            category = MarketItemCategory.BREAD,
            price = 2.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        // GENERAL_FOOD
        MarketItem(
            id = 22,
            name = "Macarrão",
            category = MarketItemCategory.GENERAL_FOOD,
            price = 3.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 23,
            name = "Arroz Integral",
            category = MarketItemCategory.GENERAL_FOOD,
            price = 4.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 24,
            name = "Feijão",
            category = MarketItemCategory.GENERAL_FOOD,
            price = 5.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        // RESTROOM
        MarketItem(
            id = 25,
            name = "Papel Higiênico",
            category = MarketItemCategory.RESTROOM,
            price = 1.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 26,
            name = "Sabonete",
            category = MarketItemCategory.RESTROOM,
            price = 0.5,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 27,
            name = "Shampoo",
            category = MarketItemCategory.RESTROOM,
            price = 3.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        // KITCHEN
        MarketItem(
            id = 28,
            name = "Esponja",
            category = MarketItemCategory.KITCHEN,
            price = 1.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 29,
            name = "Detergente",
            category = MarketItemCategory.KITCHEN,
            price = 2.0,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        MarketItem(
            id = 30,
            name = "Toalha de Papel",
            category = MarketItemCategory.KITCHEN,
            price = 1.5,
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