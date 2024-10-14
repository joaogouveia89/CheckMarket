package io.github.joaogouveia89.checkmarket.itemCreate.data.validation

import io.github.joaogouveia89.checkmarket.core.validation.Validation

private const val PRICE_REGEX = "^\\d+(\\.\\d{1,2})?\$"

class PriceValidation(private val price: String) : Validation {
    override fun isValid(): Boolean {
        if (price.isEmpty()) return false

        val regex = Regex(PRICE_REGEX)
        return regex.matches(price)
    }
}