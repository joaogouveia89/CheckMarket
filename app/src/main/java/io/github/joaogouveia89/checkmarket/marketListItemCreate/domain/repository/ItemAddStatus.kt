package io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository

import androidx.annotation.StringRes

sealed class ItemAddStatus {
    data object Loading : ItemAddStatus()
    data class Success<T>(val data: T) : ItemAddStatus()
    data class Error(@StringRes val messageRes: Int) : ItemAddStatus()
}