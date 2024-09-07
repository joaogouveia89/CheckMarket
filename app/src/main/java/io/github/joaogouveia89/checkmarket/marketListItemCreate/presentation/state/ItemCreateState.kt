package io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.state

import androidx.annotation.StringRes
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateFields
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.ItemCreateSaveUiModel

data class ItemCreateState(
    val invalidFields: List<ItemCreateFields> = emptyList(),
    val item: ItemCreateSaveUiModel = ItemCreateSaveUiModel(),
    @StringRes val errorRes: Int? = null,
    val isSaved: Boolean = false,
    val isLoading: Boolean = false
)
