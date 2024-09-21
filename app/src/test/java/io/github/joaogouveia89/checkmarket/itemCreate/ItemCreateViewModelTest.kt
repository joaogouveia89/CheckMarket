package io.github.joaogouveia89.checkmarket.itemCreate

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import io.github.joaogouveia89.checkmarket.MainDispatcherRule
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateStatus
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.usecase.ItemCreateUseCase
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.ItemCreateEvent
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.ItemCreateViewModel
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.ItemCreateSaveUiModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// Maybe create an ui state variable and not use the domain loading one, this makes the view model more testable
// check https://www.valueof.io/blog/compose-ui-state-flow-offline-first-repository

@ExperimentalCoroutinesApi
class ItemCreateViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // Mocked dependencies
    @MockK(relaxed = true)
    lateinit var itemCreateUseCase: ItemCreateUseCase

    @MockK(relaxed = true)
    lateinit var item: ItemCreateSaveUiModel

    private lateinit var viewModel: ItemCreateViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        // Use the TestDispatcher for coroutines
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = ItemCreateViewModel(itemCreateUseCase, SavedStateHandle())
    }


    @Test
    fun `when save item returns a success state`() = runTest {
        coEvery { itemCreateUseCase.saveItem(item) } returns flowOf(ItemCreateStatus.Success(1L))

        viewModel.dispatch(ItemCreateEvent.SaveItem(item))

        // Assert
        viewModel.uiState.test {
            awaitItem()
            // Second emission: success state
            assertThat(awaitItem().isSaved).isTrue()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when save item returns an error state`() = runTest {
        coEvery { itemCreateUseCase.saveItem(item) } returns flowOf(ItemCreateStatus.Error(0))

        viewModel.dispatch(ItemCreateEvent.SaveItem(item))

        // Assert
        viewModel.uiState.test {
            awaitItem()
            // Second emission: error state
            assertThat(awaitItem().errorRes).isNotNull()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}