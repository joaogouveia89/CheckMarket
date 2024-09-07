package io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.model

import io.github.joaogouveia89.checkmarket.core.data.local.entity.MarketItemEntity
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory

data class ItemCreateSaveDomainModel(
    val name: String,
    val price: String,
    val quantity: String,
    val category: MarketItemCategory
)

fun ItemCreateSaveDomainModel.asMarketItemEntity() = MarketItemEntity(
    name = name,
    price = price.toDouble(),
    quantity = quantity,
    category = category
)
