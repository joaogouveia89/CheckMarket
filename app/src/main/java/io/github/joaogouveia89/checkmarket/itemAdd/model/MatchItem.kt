package io.github.joaogouveia89.checkmarket.itemAdd.model

import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.model.ItemAddSaveUiModel

data class MatchItem(
    val id: Int,
    val name: String,
    val category: MarketItemCategory?
)

fun List<MarketItem>.asMatchItems() =
    filter { it.id != null }
        .map {
            MatchItem(
                id = it.id!!,
                name = it.name,
                category = it.category
            )
        }

fun MatchItem.asItemAddSaveUiModel() =
    ItemAddSaveUiModel(
        id = id,
        name = name,
        price = "0.0",
        quantity = "0",
        category = category ?: MarketItemCategory.FOOD
    )