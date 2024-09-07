package io.github.joaogouveia89.checkmarket.marketListItemCreate.data.repository

import io.github.joaogouveia89.checkmarket.marketListItemCreate.data.validation.ItemCreateValidator
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.model.ItemCreateSaveDomainModel
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateRepository
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.source.ItemCreateLocalDataSource
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemCreateRepositoryImpl @Inject constructor(
    private val localDataSource: ItemCreateLocalDataSource
): ItemCreateRepository {
    override suspend fun saveItem(item: ItemCreateSaveDomainModel): Flow<ItemCreateStatus> = flow {
        val invalidFields = ItemCreateValidator(item).validate()

        if(invalidFields.isNotEmpty()){
            emit(ItemCreateStatus.ValidationErrors(invalidFields))
        }else{
            emitAll(localDataSource.saveItem(item))
        }
    }
}