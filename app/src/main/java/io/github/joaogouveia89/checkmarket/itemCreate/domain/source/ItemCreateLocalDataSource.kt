package io.github.joaogouveia89.checkmarket.itemCreate.domain.source

import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.itemCreate.domain.repository.ItemCreateStatus
import kotlinx.coroutines.flow.Flow

interface ItemCreateLocalDataSource {
    suspend fun saveItem(item: MarketItem): Flow<ItemCreateStatus>
}