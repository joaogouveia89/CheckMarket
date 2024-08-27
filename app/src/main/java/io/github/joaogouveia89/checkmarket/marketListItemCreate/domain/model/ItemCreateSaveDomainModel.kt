package io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.model

import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory

data class ItemCreateSaveDomainModel(
    val name: String,
    val price: Double,
    val quantity: String,
    val category: MarketItemCategory
)
