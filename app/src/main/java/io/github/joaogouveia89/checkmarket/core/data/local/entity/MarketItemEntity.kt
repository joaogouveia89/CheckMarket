package io.github.joaogouveia89.checkmarket.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory

// TODO: CHECK MOVIE APP TO IMPLEMENT THE ROOM DB AND DI
@Entity(tableName = "MarketItem")
data class MarketItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: String,
    val category: MarketItemCategory
)


class MarketItemCategoryConverter{
    @TypeConverter
    fun fromCategory(category: MarketItemCategory) = category.id

    @TypeConverter
    fun toCategory(categoryId: Int) = MarketItemCategory.entries.find { it.id == categoryId }
}