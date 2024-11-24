package io.github.joaogouveia89.checkmarket.itemEdit.domain.repository

import io.github.joaogouveia89.checkmarket.itemEdit.domain.model.ItemEditDomainModel
import kotlinx.coroutines.flow.Flow

interface ItemEditRepository {
    suspend fun saveItem(item: ItemEditDomainModel): Flow<ItemEditStatus>
}