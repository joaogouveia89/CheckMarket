package io.github.joaogouveia89.checkmarket.marketList.domain.repository

import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import kotlinx.coroutines.flow.Flow

interface MarketListRepository {
    suspend fun fetchItems(): Flow<FetchItemsStatus>
}