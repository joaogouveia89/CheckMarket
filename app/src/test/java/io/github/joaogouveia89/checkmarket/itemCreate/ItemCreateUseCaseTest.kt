package io.github.joaogouveia89.checkmarket.itemCreate

import app.cash.turbine.test
import io.github.joaogouveia89.checkmarket.MainDispatcherRule
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.marketListItemCreate.data.usecase.ItemCreateUseCaseImpl
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.model.ItemSaveDomainModel
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateFields
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateRepository
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateStatus
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.usecase.ItemCreateUseCase
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.ItemCreateSaveUiModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ItemCreateUseCaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // Mocked dependencies
    @MockK(relaxed = true)
    lateinit var repository: ItemCreateRepository

    @MockK(relaxed = true)
    lateinit var validationErrors: List<ItemCreateFields>

    private val itemUiModel = ItemCreateSaveUiModel(
        name = "apple",
        price = "1.0",
        quantity = "1",
        category = MarketItemCategory.FOOD
    )

    private val itemDomainModel = ItemSaveDomainModel(
        name = "Apple",
        price = "1.0",
        quantity = "1",
        category = MarketItemCategory.FOOD
    )

    private lateinit var useCase: ItemCreateUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        // Use the TestDispatcher for coroutines
        Dispatchers.setMain(StandardTestDispatcher())
        useCase = ItemCreateUseCaseImpl(repository)
    }

    @Test
    fun `when save item returns a success state`() = runTest {
        coEvery { repository.saveItem(itemDomainModel) } returns flowOf(ItemCreateStatus.Success(1L))
        val response = useCase.saveItem(itemUiModel)

        // Assert
        response.test {
            awaitItem()
            // Second emission: success state
            assertEquals(awaitItem(), ItemCreateStatus.Success(1L))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when save item returns an error state`() = runTest {
        coEvery { repository.saveItem(itemDomainModel) } returns flowOf(ItemCreateStatus.Error(0))
        val response = useCase.saveItem(itemUiModel)

        // Assert
        response.test {
            awaitItem()
            // Second emission: success state
            assertEquals(awaitItem(), ItemCreateStatus.Error(0))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when save item returns validation errors`() = runTest {
        coEvery { repository.saveItem(itemDomainModel) } returns flowOf(
            ItemCreateStatus.ValidationErrors(
                validationErrors
            )
        )

        val response = useCase.saveItem(itemUiModel)

        // Assert
        response.test {
            awaitItem()
            // Second emission: success state
            assertEquals(awaitItem(), ItemCreateStatus.ValidationErrors(validationErrors))
            cancelAndIgnoreRemainingEvents()
        }
    }
}