package io.github.joaogouveia89.checkmarket.itemCreate.data.validation

import io.github.joaogouveia89.checkmarket.itemCreate.domain.model.ItemSaveDomainModel
import io.github.joaogouveia89.checkmarket.itemCreate.domain.repository.ItemCreateFields
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class ItemCreateValidator(private val itemCreateSaveDomainModel: ItemSaveDomainModel) {
    suspend fun validate(): List<ItemCreateFields> = coroutineScope {
        val validationErrors = listOf(
            async { checkNameValidation(itemCreateSaveDomainModel.name) },
            async { checkPriceValidation(itemCreateSaveDomainModel.price) },
            async { checkQuantityValidation(itemCreateSaveDomainModel.quantity) }
        )
        validationErrors.awaitAll().filterNotNull()
    }

    private fun checkNameValidation(name: String): ItemCreateFields? {
        val nameValidation = NameValidation(name)

        return if (!nameValidation.isValid()) {
            ItemCreateFields.NAME
        } else null
    }

    private fun checkPriceValidation(price: String): ItemCreateFields? {
        val priceValidation = PriceValidation(price)
        return if (!priceValidation.isValid()) {
            ItemCreateFields.PRICE
        } else null
    }

    private fun checkQuantityValidation(quantity: String): ItemCreateFields? {
        val quantityValidation = QuantityValidation(quantity)
        return if (!quantityValidation.isValid()) {
            ItemCreateFields.QUANTITY
        } else null
    }
}