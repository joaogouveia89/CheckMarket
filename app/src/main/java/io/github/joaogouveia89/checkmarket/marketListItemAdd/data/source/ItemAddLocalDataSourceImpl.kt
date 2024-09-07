package io.github.joaogouveia89.checkmarket.marketListItemAdd.data.source

import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.source.ItemAddLocalDataSource
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemAddStatus
import kotlinx.coroutines.flow.Flow

class ItemAddLocalDataSourceImpl : ItemAddLocalDataSource {
    override suspend fun saveItem(item: MarketItem): Flow<ItemAddStatus> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchItems(): Flow<List<MarketItem>> {
        TODO("Not yet implemented")
    }
}