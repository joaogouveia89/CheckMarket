package io.github.joaogouveia89.checkmarket.marketListItemCreate.data.source

import android.util.Log
import io.github.joaogouveia89.checkmarket.BuildConfig
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.asMarketItemEntity
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateStatus
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.source.ItemCreateLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "ItemCreateLocalDataSourceImpl"

class ItemCreateLocalDataSourceImpl @Inject constructor(
    private val marketItemDao: MarketItemDao
) : ItemCreateLocalDataSource {
    override suspend fun saveItem(item: MarketItem): Flow<ItemCreateStatus> = flow {
        try {
            val itemId = marketItemDao.insert(item.asMarketItemEntity())
            emit(ItemCreateStatus.Success(itemId))
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "saveItem: ${e.message}")
            }

            emit(ItemCreateStatus.Error(R.string.error_saving_item))
        }
    }
}