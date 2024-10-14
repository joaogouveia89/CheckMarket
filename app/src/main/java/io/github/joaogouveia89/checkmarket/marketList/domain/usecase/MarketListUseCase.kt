package io.github.joaogouveia89.checkmarket.marketList.domain.usecase

import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import kotlinx.coroutines.flow.Flow

interface MarketListUseCase {
    suspend fun fetchItems(): Flow<FetchItemsStatus>
}