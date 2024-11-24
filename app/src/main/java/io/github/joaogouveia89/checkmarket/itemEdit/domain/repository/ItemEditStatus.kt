package io.github.joaogouveia89.checkmarket.itemEdit.domain.repository

import androidx.annotation.StringRes
import io.github.joaogouveia89.checkmarket.itemCreate.domain.repository.ItemCreateFields

sealed class ItemEditStatus {
    data object Idle : ItemEditStatus()
    data object Loading : ItemEditStatus()
    data class Success(val success: Boolean) : ItemEditStatus()
    data class Error(@StringRes val messageRes: Int) : ItemEditStatus()
    data class ValidationErrors(val invalidFields: List<ItemCreateFields>) : ItemEditStatus()
}
