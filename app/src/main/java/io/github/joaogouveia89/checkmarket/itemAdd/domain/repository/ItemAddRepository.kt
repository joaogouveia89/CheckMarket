package io.github.joaogouveia89.checkmarket.itemAdd.domain.repository

import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.state.ItemAddSaveItemState
import kotlinx.coroutines.flow.Flow

interface ItemAddRepository {
    suspend fun saveItem(item: MarketItem): Flow<ItemAddSaveItemState>
    suspend fun fetchItems(): Flow<FetchItemsStatus>
}