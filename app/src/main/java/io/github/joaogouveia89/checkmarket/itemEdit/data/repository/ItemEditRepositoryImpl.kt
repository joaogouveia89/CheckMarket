package io.github.joaogouveia89.checkmarket.itemEdit.data.repository

import io.github.joaogouveia89.checkmarket.itemEdit.domain.model.ItemEditDomainModel
import io.github.joaogouveia89.checkmarket.itemEdit.domain.repository.ItemEditRepository
import io.github.joaogouveia89.checkmarket.itemEdit.domain.repository.ItemEditStatus
import io.github.joaogouveia89.checkmarket.itemEdit.domain.source.ItemEditLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemEditRepositoryImpl @Inject constructor(
    dataSource: ItemEditLocalDataSource
): ItemEditRepository {
    override suspend fun saveItem(item: ItemEditDomainModel): Flow<ItemEditStatus> = flow{
        emit(ItemEditStatus.Loading)
        TODO("Not yet implemented")
    }
}