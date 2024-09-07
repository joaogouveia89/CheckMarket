package io.github.joaogouveia89.checkmarket.marketListItemCreate.data.usecase

import io.github.joaogouveia89.checkmarket.core.util.ktx.capitalizeFirstLetters
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateRepository
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateStatus
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.usecase.ItemCreateUseCase
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.ItemCreateSaveUiModel
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.asDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemCreateUseCaseImpl @Inject constructor(
    private val repository: ItemCreateRepository
) : ItemCreateUseCase {
    override suspend fun saveItem(item: ItemCreateSaveUiModel): Flow<ItemCreateStatus> = flow {

        val itemCreateDomain = item.copy(
            name = item.name.capitalizeFirstLetters()
        ).asDomainModel()

        emitAll(repository.saveItem(itemCreateDomain))
    }
}