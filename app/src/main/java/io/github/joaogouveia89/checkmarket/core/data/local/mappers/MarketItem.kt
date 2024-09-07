package io.github.joaogouveia89.checkmarket.core.data.local.mappers

import io.github.joaogouveia89.checkmarket.core.data.local.entity.MarketItemEntity
import io.github.joaogouveia89.checkmarket.core.model.MarketItem

fun MarketItemEntity.asMarketItem() = MarketItem(
    id = id,
    name = name,
    price = price,
    quantity = quantity,
    category = category,
    createdAt = createdAt,
    updatedAt = updatedAt
)