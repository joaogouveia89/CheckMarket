package io.github.joaogouveia89.checkmarket.itemCreate.domain.model

import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory

data class ItemSaveDomainModel(
    val name: String,
    val price: String,
    val quantity: String,
    val category: MarketItemCategory
)


fun ItemSaveDomainModel.asMarketItem() = MarketItem(
    name = name,
    price = price.toDouble(),
    quantity = quantity,
    category = category
)
