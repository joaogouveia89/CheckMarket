package io.github.joaogouveia89.checkmarket.itemCreate.data.usecase

import io.github.joaogouveia89.checkmarket.core.util.ktx.capitalizeFirstLetters
import io.github.joaogouveia89.checkmarket.itemCreate.domain.repository.ItemCreateRepository
import io.github.joaogouveia89.checkmarket.itemCreate.domain.repository.ItemCreateStatus
import io.github.joaogouveia89.checkmarket.itemCreate.domain.usecase.ItemCreateUseCase
import io.github.joaogouveia89.checkmarket.itemCreate.presentation.model.ItemCreateSaveUiModel
import io.github.joaogouveia89.checkmarket.itemCreate.presentation.model.asDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

fun ItemCreateSaveUiModel.sanitize() = ItemCreateSaveUiModel(
    name = name.capitalizeFirstLetters().trim(),
    price = price.trim(),
    quantity = quantity.trim(),
    category = category
)

class ItemCreateUseCaseImpl @Inject constructor(
    private val repository: ItemCreateRepository
) : ItemCreateUseCase {
    override suspend fun saveItem(item: ItemCreateSaveUiModel): Flow<ItemCreateStatus> = flow {
        emit(ItemCreateStatus.Loading)

        emitAll(repository.saveItem(item.sanitize().asDomainModel()))
    }
}