package io.github.joaogouveia89.checkmarket.itemCreate.domain.repository

import io.github.joaogouveia89.checkmarket.itemCreate.domain.model.ItemSaveDomainModel
import kotlinx.coroutines.flow.Flow

enum class ItemCreateFields {
    NAME,
    PRICE,
    QUANTITY
}


interface ItemCreateRepository {
    suspend fun saveItem(item: ItemSaveDomainModel): Flow<ItemCreateStatus>
}