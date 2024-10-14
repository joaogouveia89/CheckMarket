package io.github.joaogouveia89.checkmarket.marketList.data.usecase

import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.marketList.domain.repository.MarketListRepository
import io.github.joaogouveia89.checkmarket.marketList.domain.usecase.MarketListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarketListUseCaseImpl @Inject constructor(
    private val repository: MarketListRepository
) : MarketListUseCase {
    override suspend fun fetchItems(): Flow<FetchItemsStatus> =
        repository.fetchItems()
}