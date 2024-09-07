package io.github.joaogouveia89.checkmarket.marketListItemAdd.data.source

import android.util.Log
import io.github.joaogouveia89.checkmarket.BuildConfig
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.asMarketItemEntity
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.source.ItemAddLocalDataSource
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemAddStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "ItemAddLocalDataSourceImpl"

class ItemAddLocalDataSourceImpl @Inject constructor(
    private val marketItemDao: MarketItemDao
) : ItemAddLocalDataSource {
    override suspend fun saveItem(item: MarketItem): Flow<ItemAddStatus> = flow {
        emit(ItemAddStatus.Loading)
        try {
            val itemId = marketItemDao.insert(item.asMarketItemEntity())
            emit(ItemAddStatus.Success(itemId))
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "saveItem: ${e.message}")
            }

            emit(ItemAddStatus.Error(R.string.error_saving_item))
        }
    }

    override suspend fun fetchItems(): Flow<ItemAddStatus> = flow{
        emit(ItemAddStatus.Loading)

        try {
            val items = marketItemDao.fetchAll()
            emit(ItemAddStatus.Success(items))
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "fetchItems: ${e.message}")
            }

            emit(ItemAddStatus.Error(R.string.error_fetching_items))
        }
    }
}