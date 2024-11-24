package io.github.joaogouveia89.checkmarket.itemEdit.domain.source

import io.github.joaogouveia89.checkmarket.core.model.MarketItem


interface ItemEditLocalDataSource {
    suspend fun saveItem(item: MarketItem): Boolean
}