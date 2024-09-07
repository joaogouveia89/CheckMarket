package io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model

import androidx.compose.runtime.Stable
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.model.ItemCreateSaveDomainModel

@Stable
data class ItemCreateSaveUiModel(
    val id: Long = -1,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
    val category: MarketItemCategory = MarketItemCategory.FOOD
)

fun ItemCreateSaveUiModel.asDomainModel() =
    ItemCreateSaveDomainModel(
        name = name,
        price = price,
        quantity = quantity,
        category = category
    )