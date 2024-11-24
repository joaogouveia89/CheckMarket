package io.github.joaogouveia89.checkmarket.itemEdit.data.source

import android.util.Log
import io.github.joaogouveia89.checkmarket.BuildConfig
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.itemEdit.domain.source.ItemEditLocalDataSource
import javax.inject.Inject

private const val TAG = "ItemEditLocalDataSourceImpl"

class ItemEditLocalDataSourceImpl @Inject constructor(
    private val marketItemDao: MarketItemDao
) : ItemEditLocalDataSource {
    override suspend fun saveItem(item: MarketItem): Boolean {

        if(item.id == null) return false

        try {
            marketItemDao.update(
                id = item.id,
                price = item.price,
                quantity = item.quantity,
                updatedAt = item.updatedAt
            )
            return true

        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "saveItem: ${e.message}")
            }

            return false
        }
    }
}