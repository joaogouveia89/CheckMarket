package io.github.joaogouveia89.checkmarket.marketList.presentation.state

import androidx.annotation.StringRes
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory

data class MarketListUiState(
    val items: Map<MarketItemCategory, List<MarketItem>> = mapOf(),
    val isLoading: Boolean = false,
    @StringRes val errorRes: Int? = null,
    val showError: Boolean = false
)