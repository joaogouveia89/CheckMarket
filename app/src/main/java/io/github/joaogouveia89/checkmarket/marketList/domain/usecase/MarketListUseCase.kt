package io.github.joaogouveia89.checkmarket.marketList.domain.usecase

import kotlinx.coroutines.flow.Flow

interface MarketListUseCase {
    suspend fun fetchItems(): Flow<MarketListStatus>
}