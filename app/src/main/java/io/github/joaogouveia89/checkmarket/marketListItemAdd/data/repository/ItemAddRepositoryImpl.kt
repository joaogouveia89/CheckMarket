package io.github.joaogouveia89.checkmarket.marketListItemAdd.data.repository

import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.ItemAddRepository
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.source.ItemAddLocalDataSource
import javax.inject.Inject

class ItemAddRepositoryImpl @Inject constructor(
    private val localDataSource: ItemAddLocalDataSource
): ItemAddRepository {
    override suspend fun saveItem(item: MarketItem) = localDataSource.saveItem(item)
    override suspend fun fetchItems() = localDataSource.fetchItems()

}