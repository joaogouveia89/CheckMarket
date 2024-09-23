package io.github.joaogouveia89.checkmarket.marketListItemAdd.data.source

import android.util.Log
import io.github.joaogouveia89.checkmarket.BuildConfig
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.core.data.local.mappers.asMarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.source.ItemAddLocalDataSource
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.asMatchItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "ItemAddLocalDataSourceImpl"

class ItemAddLocalDataSourceImpl @Inject constructor(
    private val marketItemDao: MarketItemDao
) : ItemAddLocalDataSource {
    override suspend fun saveItem(item: MarketItem): Flow<FetchItemsStatus> = flow {
//        emit(ItemAddStatus.Loading)
//        try {
//            val itemId = marketItemDao.insert(item.asMarketItemEntity())
//            emit(ItemAddStatus.OnSaveSuccess(itemId))
//        } catch (e: Exception) {
//            if (BuildConfig.DEBUG) {
//                Log.e(TAG, "saveItem: ${e.message}")
//            }
//
//            emit(ItemAddStatus.Error(R.string.error_saving_item))
//        }
    }

    override suspend fun fetchItems(): Flow<FetchItemsStatus> = flow {
        emit(FetchItemsStatus.Loading)

        try {
            val items = marketItemDao
                .fetchAll()
                .map { it.asMarketItem()}
                .asMatchItems()

            emit(FetchItemsStatus.OnNewList(items))
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "fetchItems: ${e.message}")
            }

            emit(FetchItemsStatus.Error(R.string.error_fetching_items))
        }
    }
}