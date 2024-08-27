package io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model

import androidx.compose.runtime.Stable
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory

@Stable
data class ItemCreateSaveUiModel(
    val name: String,
    val price: Double,
    val quantity: String,
    val category: MarketItemCategory
)
