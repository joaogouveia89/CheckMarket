package io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.source

import androidx.annotation.StringRes

sealed class ItemCreateStatus {
    data object Loading : ItemCreateStatus()
    data class Success(val id: Long) : ItemCreateStatus()
    data class Error(@StringRes val messageRes: Int) : ItemCreateStatus()
}