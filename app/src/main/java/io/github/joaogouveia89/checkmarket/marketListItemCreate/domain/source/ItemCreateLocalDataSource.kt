package io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.source

import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateStatus
import kotlinx.coroutines.flow.Flow

interface ItemCreateLocalDataSource {
    suspend fun saveItem(item: MarketItem): Flow<ItemCreateStatus>
}