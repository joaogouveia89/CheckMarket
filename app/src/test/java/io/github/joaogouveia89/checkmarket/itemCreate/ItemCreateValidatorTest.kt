package io.github.joaogouveia89.checkmarket.itemCreate

import io.github.joaogouveia89.checkmarket.MainDispatcherRule
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.itemCreate.data.validation.ItemCreateValidator
import io.github.joaogouveia89.checkmarket.itemCreate.domain.model.ItemSaveDomainModel
import io.github.joaogouveia89.checkmarket.itemCreate.domain.repository.ItemCreateFields
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class ItemCreateValidatorTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK(relaxed = true)
    lateinit var validator: ItemCreateValidator

    private val correctItem = ItemSaveDomainModel(
        name = "Apple",
        price = "1.0",
        quantity = "1",
        category = MarketItemCategory.FOOD
    )

    @Test
    fun `Valid item should pass all validations`() = runTest {
        validator = ItemCreateValidator(correctItem)

        val result = validator.validate()

        assertTrue(result.isEmpty(), "Expected no validation errors")
    }

    @Test
    fun `Name too short should be invalid`() = runTest {
        val invalidNameItem = correctItem.copy(
            name = "a"
        )

        validator = ItemCreateValidator(invalidNameItem)

        // Assert
        val result = validator.validate()

        assertEquals(result, listOf(ItemCreateFields.NAME))
    }

    @Test
    fun `Name with exactly two characters should be valid`() = runTest {
        val validNameItem = correctItem.copy(name = "Ab")

        validator = ItemCreateValidator(validNameItem)

        val result = validator.validate()

        assertTrue(result.isEmpty(), "Expected no validation errors")
    }

    @Test
    fun `Price should not be empty`() = runTest {
        val emptyPriceItem = correctItem.copy(price = "")

        validator = ItemCreateValidator(emptyPriceItem)

        val result = validator.validate()

        assertEquals(result, listOf(ItemCreateFields.PRICE))
    }

    @Test
    fun `Price with more than two decimal places should be invalid`() = runTest {
        val invalidPriceItem = correctItem.copy(price = "1.123")

        validator = ItemCreateValidator(invalidPriceItem)

        val result = validator.validate()

        assertEquals(result, listOf(ItemCreateFields.PRICE))
    }

    @Test
    fun `Price with two decimal places should be valid`() = runTest {
        val validPriceItem = correctItem.copy(price = "1.99")

        validator = ItemCreateValidator(validPriceItem)

        val result = validator.validate()

        assertTrue(result.isEmpty(), "Expected no validation errors")
    }

    @Test
    fun `Quantity with non-numeric characters should be invalid`() = runTest {
        val invalidQuantityItem = correctItem.copy(quantity = "one")

        validator = ItemCreateValidator(invalidQuantityItem)

        val result = validator.validate()

        assertEquals(result, listOf(ItemCreateFields.QUANTITY))
    }

    @Test
    fun `Quantity with number and unit should be valid`() = runTest {
        val validQuantityItem = correctItem.copy(quantity = "5 kg")

        validator = ItemCreateValidator(validQuantityItem)

        val result = validator.validate()

        assertTrue(result.isEmpty(), "Expected no validation errors")
    }

    @Test
    fun `Multiple invalid fields should return all errors`() = runTest {
        val invalidItem = correctItem.copy(name = "a", price = "abc", quantity = "one")

        validator = ItemCreateValidator(invalidItem)

        val result = validator.validate()

        assertEquals(
            result,
            listOf(ItemCreateFields.NAME, ItemCreateFields.PRICE, ItemCreateFields.QUANTITY)
        )
    }
}