package io.github.joaogouveia89.checkmarket.marketList.data.repository

import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.marketList.domain.repository.MarketListRepository
import io.github.joaogouveia89.checkmarket.marketList.domain.source.MarketListDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarketListRepositoryImpl @Inject constructor(
    private val marketListDataSource: MarketListDataSource
) : MarketListRepository {
    override suspend fun fetchItems(): Flow<FetchItemsStatus> =
        marketListDataSource.fetchItems()
}