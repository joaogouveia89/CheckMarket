package io.github.joaogouveia89.checkmarket.itemCreate.data.validation

import io.github.joaogouveia89.checkmarket.core.validation.Validation

private const val QUANTITY_REGEX = "^\\d+\\s*(\\w+)?$"

class QuantityValidation(private val quantity: String) : Validation {
    override fun isValid(): Boolean =
        Regex(QUANTITY_REGEX)
            .matches(quantity)
}