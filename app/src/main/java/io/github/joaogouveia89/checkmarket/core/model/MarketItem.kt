package io.github.joaogouveia89.checkmarket.core.model

import kotlinx.datetime.LocalDateTime

data class MarketItem(
    val id: Int,
    val name: String,
    val category: MarketItemCategory,
    val price: Double? = null,
    val quantity: Int? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
