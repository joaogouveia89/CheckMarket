package io.github.joaogouveia89.checkmarket.core.model

import io.github.joaogouveia89.checkmarket.core.data.local.entity.MarketItemEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class MarketItem(
    val id: Int? = null,
    val name: String,
    val category: MarketItemCategory,
    val price: Double,
    val quantity: String,
    val isBought: Boolean = false,
    val createdAt: LocalDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()),
    val updatedAt: LocalDateTime = createdAt
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
