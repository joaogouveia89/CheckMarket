package io.github.joaogouveia89.checkmarket.itemCreate

import io.github.joaogouveia89.checkmarket.marketListItemCreate.data.usecase.sanitize
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.ItemCreateSaveUiModel
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class ItemCreateInputSanitizeTest {
    @Test
    fun `sanitize should trim and capitalize the name, trim price and quantity`() {
        // Given
        val rawItem = ItemCreateSaveUiModel(
            name = "   apple pie   ",
            price = " 1.99 ",
            quantity = " 10 "
        )

        // When
        val sanitizedItem = rawItem.sanitize()

        // Then
        assertEquals("Apple Pie", sanitizedItem.name)
        assertEquals("1.99", sanitizedItem.price)
        assertEquals("10", sanitizedItem.quantity)
    }

    @Test
    fun `sanitize should handle edge cases with empty and single-character strings`() {
        // Given
        val rawItem = ItemCreateSaveUiModel(
            name = " a ",
            price = " ",
            quantity = ""
        )

        // When
        val sanitizedItem = rawItem.sanitize()

        // Then
        assertEquals("A", sanitizedItem.name)
        assertEquals("", sanitizedItem.price)
        assertEquals("", sanitizedItem.quantity)
    }

}