package io.github.joaogouveia89.checkmarket.marketListItemAdd.model

import androidx.annotation.StringRes
import io.github.joaogouveia89.checkmarket.core.model.MarketItem

sealed class ItemsFetchStatus {
    data object Loading : ItemsFetchStatus()
    data class Success(val items: List<MarketItem>) : ItemsFetchStatus()
    data class Error(@StringRes val messageRes: Int) : ItemsFetchStatus()
}