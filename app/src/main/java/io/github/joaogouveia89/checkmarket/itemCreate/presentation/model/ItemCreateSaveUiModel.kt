package io.github.joaogouveia89.checkmarket.itemCreate.presentation.model

import androidx.compose.runtime.Stable
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.itemCreate.domain.model.ItemSaveDomainModel

@Stable
data class ItemCreateSaveUiModel(
    val id: Long = -1,
    val name: String = "",
    val price: String = "0",
    val quantity: String = "0",
    val category: MarketItemCategory = MarketItemCategory.FOOD
)

fun ItemCreateSaveUiModel.asDomainModel() =
    ItemSaveDomainModel(
        name = name,
        price = price,
        quantity = quantity,
        category = category
    )