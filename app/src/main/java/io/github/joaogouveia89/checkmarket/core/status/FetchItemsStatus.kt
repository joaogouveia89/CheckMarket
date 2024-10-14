package io.github.joaogouveia89.checkmarket.core.status

import androidx.annotation.StringRes
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem

sealed class FetchItemsStatus {
    data object Idle : FetchItemsStatus()
    data object Loading : FetchItemsStatus()
    data class OnNewList(val items: List<MatchItem>) : FetchItemsStatus() // Change the list to MarketItem and on the Add screen map everybody to MatchItem
    data class Error(@StringRes val messageRes: Int) : FetchItemsStatus()
}