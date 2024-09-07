package io.github.joaogouveia89.checkmarket.marketListItemCreate.data.validation

import io.github.joaogouveia89.checkmarket.core.validation.Validation

class NameValidation(private val name: String): Validation {
    override fun isValid(): Boolean =
        name.length > 1
}