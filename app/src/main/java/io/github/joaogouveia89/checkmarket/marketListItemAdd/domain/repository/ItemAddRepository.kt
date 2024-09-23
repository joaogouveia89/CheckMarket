package io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository

import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import kotlinx.coroutines.flow.Flow

interface ItemAddRepository {
    suspend fun saveItem(item: MarketItem): Flow<FetchItemsStatus>
    suspend fun fetchItems(): Flow<FetchItemsStatus>
}