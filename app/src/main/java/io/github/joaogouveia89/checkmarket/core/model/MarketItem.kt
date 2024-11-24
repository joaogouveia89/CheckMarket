package io.github.joaogouveia89.checkmarket.core.model

import io.github.joaogouveia89.checkmarket.core.data.local.entity.MarketItemEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class MarketItem(
    val id: Long? = null,
    val name: String,
    val category: MarketItemCategory,
    val price: Double,
    val quantity: String,
    val isBought: Boolean = false,
    val createdAt: Instant = Clock.System.now(),
    val updatedAt: Instant = createdAt
)

fun MarketItem.asMarketItemEntity() = MarketItemEntity(
    name = name,
    price = price,
    quantity = quantity,
    category = category,
    isBought = isBought,
    createdAt = createdAt,
    updatedAt = updatedAt
)
