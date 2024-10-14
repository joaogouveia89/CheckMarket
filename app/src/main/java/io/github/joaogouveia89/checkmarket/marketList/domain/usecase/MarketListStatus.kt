package io.github.joaogouveia89.checkmarket.marketList.domain.usecase

import androidx.annotation.StringRes
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory

sealed class MarketListStatus {
    data object Idle : MarketListStatus()
    data object Loading : MarketListStatus()
    data class Success(val items: Map<MarketItemCategory, List<MarketItem>>) : MarketListStatus()
    data class Error(@StringRes val messageRes: Int) : MarketListStatus()
}