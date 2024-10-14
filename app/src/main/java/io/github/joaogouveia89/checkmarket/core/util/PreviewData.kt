package io.github.joaogouveia89.checkmarket.core.util

import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import kotlinx.datetime.Clock

object PreviewData {
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

    val categorizedList = marketItemsList.groupBy { it.category }
}