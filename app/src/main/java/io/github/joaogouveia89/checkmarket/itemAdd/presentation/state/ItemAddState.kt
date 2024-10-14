package io.github.joaogouveia89.checkmarket.itemAdd.presentation.state

import androidx.annotation.StringRes
import io.github.joaogouveia89.checkmarket.itemAdd.model.MatchItem

data class ItemAddState(
    val matchItems: List<MatchItem> = listOf(),
    @StringRes val errorRes: Int? = null,
    val showError: Boolean = false,
    val isSaved: Boolean = false,
    val itemAddItemContentState: ItemAddContentState = ItemAddContentState.NO_QUERY_TYPED
)