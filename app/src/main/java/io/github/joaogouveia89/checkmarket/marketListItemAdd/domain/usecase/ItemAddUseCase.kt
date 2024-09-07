package io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase

import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.ItemAddStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.model.ItemAddSaveUiModel
import kotlinx.coroutines.flow.Flow

interface ItemAddUseCase {
    suspend fun saveItem(item: ItemAddSaveUiModel): Flow<ItemAddStatus>
    suspend fun fetchItems(): Flow<ItemAddStatus>

}