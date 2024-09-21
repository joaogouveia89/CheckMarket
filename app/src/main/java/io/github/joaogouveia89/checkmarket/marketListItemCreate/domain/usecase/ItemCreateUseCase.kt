package io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.usecase

import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateStatus
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.ItemCreateSaveUiModel
import kotlinx.coroutines.flow.Flow

interface ItemCreateUseCase {
    suspend fun saveItem(item: ItemCreateSaveUiModel): Flow<ItemCreateStatus>
}