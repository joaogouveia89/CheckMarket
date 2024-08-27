package io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.source

import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.model.ItemCreateSaveDomainModel

interface ItemCreateLocalDataSource {
    suspend fun saveItem(item: ItemCreateSaveDomainModel)
}