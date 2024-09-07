package io.github.joaogouveia89.checkmarket.core.model

import io.github.joaogouveia89.checkmarket.core.data.local.entity.MarketItemEntity
import kotlinx.datetime.LocalDateTime

data class MarketItem(
    val id: Int? = null,
    val name: String,
    val category: MarketItemCategory,
    val price: Double,
    val quantity: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)

fun MarketItem.asMarketItemEntity() = MarketItemEntity(
    name = name,
    price = price,
    quantity = quantity,
    category = category
)
