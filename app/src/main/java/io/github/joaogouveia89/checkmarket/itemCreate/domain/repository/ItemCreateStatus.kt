package io.github.joaogouveia89.checkmarket.itemCreate.domain.repository

import androidx.annotation.StringRes

sealed class ItemCreateStatus {
    data object Idle : ItemCreateStatus()
    data object Loading : ItemCreateStatus()
    data class Success(val id: Long) : ItemCreateStatus()
    data class Error(@StringRes val messageRes: Int) : ItemCreateStatus()
    data class ValidationErrors(val invalidFields: List<ItemCreateFields>) : ItemCreateStatus()
}