package io.github.joaogouveia89.checkmarket.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.github.joaogouveia89.checkmarket.core.data.local.entity.MarketItemEntity

@Dao
interface MarketItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(marketItem: MarketItemEntity)
}