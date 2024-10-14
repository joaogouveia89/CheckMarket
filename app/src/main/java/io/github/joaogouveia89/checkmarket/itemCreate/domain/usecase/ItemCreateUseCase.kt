package io.github.joaogouveia89.checkmarket.itemCreate.domain.usecase

import io.github.joaogouveia89.checkmarket.itemCreate.domain.repository.ItemCreateStatus
import io.github.joaogouveia89.checkmarket.itemCreate.presentation.model.ItemCreateSaveUiModel
import kotlinx.coroutines.flow.Flow

interface ItemCreateUseCase {
    suspend fun saveItem(item: ItemCreateSaveUiModel): Flow<ItemCreateStatus>
}