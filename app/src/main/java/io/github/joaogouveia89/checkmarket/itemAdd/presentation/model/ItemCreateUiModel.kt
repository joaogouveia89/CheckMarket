package io.github.joaogouveia89.checkmarket.itemAdd.presentation.model

import androidx.compose.runtime.Stable
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory

@Stable
data class ItemAddSaveUiModel(
    val id: Int = -1,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
    val category: MarketItemCategory = MarketItemCategory.FOOD
)

fun ItemAddSaveUiModel.asMarketItem() =
    MarketItem(
        name = name,
        price = price.toDouble(),
        quantity = quantity,
        category = category
    )