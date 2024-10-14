package io.github.joaogouveia89.checkmarket.marketList.data.usecase

import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.marketList.domain.repository.MarketListRepository
import io.github.joaogouveia89.checkmarket.marketList.domain.usecase.MarketListStatus
import io.github.joaogouveia89.checkmarket.marketList.domain.usecase.MarketListUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarketListUseCaseImpl @Inject constructor(
    private val repository: MarketListRepository
) : MarketListUseCase {
    override suspend fun fetchItems(): Flow<MarketListStatus> =
        repository
            .fetchItems()
            .map { fetchItemStatus ->
                when (fetchItemStatus) {
                    is FetchItemsStatus.OnNewList -> {
                        val groupedItems = fetchItemStatus.items.groupBy { item -> item.category }
                        MarketListStatus.Success(groupedItems)
                    }

                    is FetchItemsStatus.Loading -> MarketListStatus.Loading
                    is FetchItemsStatus.Error -> MarketListStatus.Error(fetchItemStatus.messageRes)
                    FetchItemsStatus.Idle -> MarketListStatus.Idle
                }
            }
}