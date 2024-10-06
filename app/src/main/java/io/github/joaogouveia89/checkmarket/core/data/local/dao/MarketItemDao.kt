package io.github.joaogouveia89.checkmarket.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.joaogouveia89.checkmarket.core.data.local.entity.MarketItemEntity

@Dao
interface MarketItemDao {

    @Query("SELECT * FROM MarketItem GROUP BY name")
    suspend fun fetchAllUnique(): List<MarketItemEntity>

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insert(marketItem: MarketItemEntity): Long
}