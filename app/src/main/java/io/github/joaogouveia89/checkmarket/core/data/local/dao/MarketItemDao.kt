package io.github.joaogouveia89.checkmarket.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.joaogouveia89.checkmarket.core.data.local.entity.MarketItemEntity
import kotlinx.datetime.Instant

@Dao
interface MarketItemDao {

    /*
    * Fetch all items from today, and the older ones which are not bought yet
     */
    @Query("SELECT * FROM MarketItem WHERE (DATE(updatedAt) = DATE('now')) OR (DATE(updatedAt) = DATE('now')) and isBought = 0")
    suspend fun fetchMarketList(): List<MarketItemEntity>

    @Query("SELECT * FROM MarketItem GROUP BY name")
    suspend fun fetchAllUnique(): List<MarketItemEntity>

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insert(marketItem: MarketItemEntity): Long

    @Query("UPDATE MarketItem SET price = :price, quantity = :quantity, updatedAt = :updatedAt WHERE id = :id")
    suspend fun update(id: Long, price: Double, quantity: String, updatedAt: Instant)
}