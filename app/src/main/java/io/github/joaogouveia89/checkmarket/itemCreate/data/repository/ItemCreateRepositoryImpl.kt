package io.github.joaogouveia89.checkmarket.itemCreate.data.repository

import io.github.joaogouveia89.checkmarket.itemCreate.data.validation.ItemCreateValidator
import io.github.joaogouveia89.checkmarket.itemCreate.domain.model.ItemSaveDomainModel
import io.github.joaogouveia89.checkmarket.itemCreate.domain.model.asMarketItem
import io.github.joaogouveia89.checkmarket.itemCreate.domain.repository.ItemCreateRepository
import io.github.joaogouveia89.checkmarket.itemCreate.domain.repository.ItemCreateStatus
import io.github.joaogouveia89.checkmarket.itemCreate.domain.source.ItemCreateLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemCreateRepositoryImpl @Inject constructor(
    private val localDataSource: ItemCreateLocalDataSource
) : ItemCreateRepository {
    override suspend fun saveItem(item: ItemSaveDomainModel): Flow<ItemCreateStatus> = flow {
        val invalidFields = ItemCreateValidator(item).validate()

        if (invalidFields.isNotEmpty()) {
            emit(ItemCreateStatus.ValidationErrors(invalidFields))
        } else {
            emitAll(localDataSource.saveItem(item.asMarketItem()))
        }
    }
}