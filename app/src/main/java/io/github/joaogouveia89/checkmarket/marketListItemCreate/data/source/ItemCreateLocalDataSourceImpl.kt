package io.github.joaogouveia89.checkmarket.marketListItemCreate.data.source

import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.model.ItemCreateSaveDomainModel
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.source.ItemCreateLocalDataSource
import javax.inject.Inject

class ItemCreateLocalDataSourceImpl @Inject constructor(
    private val marketItemDao: MarketItemDao
): ItemCreateLocalDataSource {
    override suspend fun saveItem(item: ItemCreateSaveDomainModel) {
        TODO("Not yet implemented")
    }
}