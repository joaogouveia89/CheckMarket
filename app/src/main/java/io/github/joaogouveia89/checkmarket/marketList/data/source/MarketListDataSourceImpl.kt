package io.github.joaogouveia89.checkmarket.marketList.data.source

import android.util.Log
import io.github.joaogouveia89.checkmarket.BuildConfig
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.core.data.local.mappers.asMarketItem
import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.marketList.domain.source.MarketListDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "MarketListDataSourceImpl"

class MarketListDataSourceImpl @Inject constructor(
    private val marketItemDao: MarketItemDao
) : MarketListDataSource {
    override suspend fun fetchItems(): Flow<FetchItemsStatus> = flow {
        emit(FetchItemsStatus.Loading)

        try {
            val items = marketItemDao
                .fetchMarketList()
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