package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.state

import androidx.annotation.StringRes
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem

data class ItemAddState(
    val matchItems: List<MatchItem> = listOf(),
    @StringRes val errorRes: Int? = null,
    val showError: Boolean = false,
    val isLoading: Boolean = false
)