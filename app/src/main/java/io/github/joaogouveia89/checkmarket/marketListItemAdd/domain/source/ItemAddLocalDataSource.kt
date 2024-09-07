package io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.source

import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.ItemAddStatus
import kotlinx.coroutines.flow.Flow

interface ItemAddLocalDataSource {
    suspend fun saveItem(item: MarketItem): Flow<ItemAddStatus>
    suspend fun fetchItems(): Flow<ItemAddStatus>
}