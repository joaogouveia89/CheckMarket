package io.github.joaogouveia89.checkmarket.marketList.domain.source

import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import kotlinx.coroutines.flow.Flow

interface MarketListDataSource {
    suspend fun fetchItems(): Flow<FetchItemsStatus>
}