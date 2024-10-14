package io.github.joaogouveia89.checkmarket.itemAdd.data.source

import android.util.Log
import io.github.joaogouveia89.checkmarket.BuildConfig
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.core.data.local.mappers.asMarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.asMarketItemEntity
import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.itemAdd.domain.source.ItemAddLocalDataSource
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.state.ItemAddSaveItemState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "ItemAddLocalDataSourceImpl"

class ItemAddLocalDataSourceImpl @Inject constructor(
    private val marketItemDao: MarketItemDao
) : ItemAddLocalDataSource {
    override suspend fun saveItem(item: MarketItem): Flow<ItemAddSaveItemState> = flow {
        emit(ItemAddSaveItemState.Loading)
        try {
            val itemId = marketItemDao.insert(item.asMarketItemEntity())
            emit(ItemAddSaveItemState.Success(itemId))
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "saveItem: ${e.message}")
            }

            emit(ItemAddSaveItemState.Error(R.string.error_saving_item))
        }
    }

    override suspend fun fetchItems(): Flow<FetchItemsStatus> = flow {
        emit(FetchItemsStatus.Loading)

        try {
            val items = marketItemDao
                .fetchAllUnique()
                .map { it.asMarketItem() }

            emit(FetchItemsStatus.OnNewList(items))
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "fetchItems: ${e.message}")
            }

            emit(FetchItemsStatus.Error(R.string.error_fetching_items))
        }
    }
}