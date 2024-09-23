package io.github.joaogouveia89.checkmarket.marketListItemAdd.model

import io.github.joaogouveia89.checkmarket.core.model.MarketItem

data class MatchItem(
    val id: Int,
    val name: String
)

fun List<MarketItem>.asMatchItems() =
    filter { it.id != null }
        .map {
            MatchItem(
                id = it.id!!,
                name = it.name
            )
        }