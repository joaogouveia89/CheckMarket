package io.github.joaogouveia89.checkmarket.marketListItemAdd.data.usecase

import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.ItemAddRepository
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.ItemAddStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase.ItemAddUseCase
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.model.ItemAddSaveUiModel
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.model.asMarketItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemAddUseCaseImpl @Inject constructor(
    private val repository: ItemAddRepository
): ItemAddUseCase {
    override suspend fun saveItem(item: ItemAddSaveUiModel): Flow<ItemAddStatus> =
        repository.saveItem(item.asMarketItem())

    override suspend fun fetchItems(): Flow<ItemAddStatus> =
        repository.fetchItems()
}