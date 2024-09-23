package io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.source

import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.FetchItemsStatus
import kotlinx.coroutines.flow.Flow

interface ItemAddLocalDataSource {
    suspend fun saveItem(item: MarketItem): Flow<FetchItemsStatus>
    suspend fun fetchItems(): Flow<FetchItemsStatus>
}