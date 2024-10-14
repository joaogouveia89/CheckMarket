package io.github.joaogouveia89.checkmarket.marketList

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import io.github.joaogouveia89.checkmarket.MainDispatcherRule
import io.github.joaogouveia89.checkmarket.core.util.PreviewData
import io.github.joaogouveia89.checkmarket.marketList.domain.usecase.MarketListStatus
import io.github.joaogouveia89.checkmarket.marketList.domain.usecase.MarketListUseCase
import io.github.joaogouveia89.checkmarket.marketList.presentation.MarketListViewModel
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

class MarketListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // Mocked dependencies
    @MockK(relaxed = true)
    lateinit var marketListUseCase: MarketListUseCase

    private lateinit var viewModel: MarketListViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        // Use the TestDispatcher for coroutines
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `when have a success state return the market items list`() = runTest {

        coEvery { marketListUseCase.fetchItems() } returns flowOf(
            MarketListStatus.Success(
                PreviewData.categorizedList
            )
        )

        viewModel = MarketListViewModel(marketListUseCase)

        viewModel.uiState.test {
            awaitItem()
            // Second emission: success state
            assertThat(awaitItem().items).isEqualTo(PreviewData.categorizedList)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when have a error state return the error message and show error`() = runTest {
        val errorRes = 123
        coEvery { marketListUseCase.fetchItems() } returns flowOf(
            MarketListStatus.Error(
                messageRes = errorRes
            )
        )

        viewModel = MarketListViewModel(marketListUseCase)

        viewModel.uiState.test {
            awaitItem()
            val result = awaitItem()
            // Second emission: success state
            assertThat(result.errorRes).isEqualTo(errorRes)
            assertThat(result.showError).isEqualTo(true)
            cancelAndIgnoreRemainingEvents()
        }
    }
}