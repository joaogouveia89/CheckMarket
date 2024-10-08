package io.github.joaogouveia89.checkmarket.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import kotlinx.datetime.LocalDateTime

// TODO: CHECK MOVIE APP TO IMPLEMENT THE ROOM DB AND DI
@Entity(tableName = "MarketItem")
data class MarketItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: String,
    val category: MarketItemCategory,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)


class MarketItemCategoryConverter {
    @TypeConverter
    fun fromCategory(category: MarketItemCategory) = category.id

    @TypeConverter
    fun toCategory(categoryId: Int) = MarketItemCategory.entries.find { it.id == categoryId }
}

class LocalDateTimeConverter {
    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime): String {
        return dateTime.toString()
    }

    @TypeConverter
    fun toLocalDateTime(dateTimeString: String): LocalDateTime {
        return LocalDateTime.parse(dateTimeString)
    }
}