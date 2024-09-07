package io.github.joaogouveia89.checkmarket.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.core.data.local.entity.LocalDateTimeConverter
import io.github.joaogouveia89.checkmarket.core.data.local.entity.MarketItemCategoryConverter
import io.github.joaogouveia89.checkmarket.core.data.local.entity.MarketItemEntity


const val LOCAL_DATABASE_NAME = "check_market_database"

@TypeConverters(MarketItemCategoryConverter::class, LocalDateTimeConverter::class)
@Database(
    entities = [MarketItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CheckMarketDatabase: RoomDatabase() {
    abstract fun marketItemDao(): MarketItemDao
}