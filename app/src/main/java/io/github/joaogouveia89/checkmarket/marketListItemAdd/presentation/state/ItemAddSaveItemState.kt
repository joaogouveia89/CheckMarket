package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.state

import androidx.annotation.StringRes

sealed class ItemAddSaveItemState {
    data object Idle : ItemAddSaveItemState()
    data object Loading : ItemAddSaveItemState()
    data class Error(@StringRes val messageRes: Int) : ItemAddSaveItemState()
    data class Success(val itemId: Long) : ItemAddSaveItemState()
}