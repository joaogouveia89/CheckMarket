package io.github.joaogouveia89.checkmarket.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "MarketItem")
data class MarketItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val price: Double,
    val quantity: String,
    val category: MarketItemCategory,
    val isBought: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant
)


class MarketItemCategoryConverter {
    @TypeConverter
    fun fromCategory(category: MarketItemCategory) = category.id

    @TypeConverter
    fun toCategory(categoryId: Int) = MarketItemCategory.entries.find { it.id == categoryId }
}

class InstantConverter {
    @TypeConverter
    fun fromLocalDateTime(dateTime: Instant): String {
        return dateTime.toString()
    }

    @TypeConverter
    fun toLocalDateTime(dateTimeString: String): Instant {
        return Instant.parse(dateTimeString)
    }
}