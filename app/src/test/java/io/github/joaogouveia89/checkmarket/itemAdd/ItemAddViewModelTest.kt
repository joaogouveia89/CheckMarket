package io.github.joaogouveia89.checkmarket.itemAdd

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import io.github.joaogouveia89.checkmarket.MainDispatcherRule
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.itemAdd.domain.usecase.ItemAddUseCase
import io.github.joaogouveia89.checkmarket.itemAdd.domain.usecase.QuerySimilarityEvaluationStatus
import io.github.joaogouveia89.checkmarket.itemAdd.model.MatchItem
import io.github.joaogouveia89.checkmarket.itemAdd.model.asMatchItems
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.ItemAddEvent
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.ItemAddViewModel
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.state.ItemAddContentState
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.state.ItemAddSaveItemState
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
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ItemAddViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val testDispatcher = StandardTestDispatcher()

    @MockK(relaxed = true)
    lateinit var itemAddUseCase: ItemAddUseCase

    private lateinit var viewModel: ItemAddViewModel

    private val appleMatches = listOf(MarketItem(id = 1, name = "Apple", quantity = "1", price = 2.0, category = MarketItemCategory.FOOD))

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        // Use the TestDispatcher for coroutines
        Dispatchers.setMain(testDispatcher)

        coEvery { itemAddUseCase.fetchItems() } returns flowOf(FetchItemsStatus.OnNewList(appleMatches))

        viewModel = ItemAddViewModel(itemAddUseCase)
    }

    @Test
    fun `initial state should be idle with no error and type query message shown`() = runTest {
        val initialState = viewModel.uiState.value

        assertThat(initialState.isSaved).isFalse()
        assertThat(initialState.matchItems).isEmpty()
        assertThat(initialState.showError).isFalse()
        assertEquals(initialState.itemAddItemContentState, ItemAddContentState.NO_QUERY_TYPED)
    }

    @Test
    fun `query update should trigger the similarity evaluation`() = runTest {

        val newQuery = "Apple"

        coEvery { itemAddUseCase.evaluateQuerySimilarity(any(), any()) } returns flowOf(QuerySimilarityEvaluationStatus.Success(
            appleMatches.asMatchItems()
        ))

        viewModel.dispatch(ItemAddEvent.UpdateQuery(newQuery))

        testDispatcher.scheduler.advanceUntilIdle()
        viewModel.uiState.test {
            awaitItem()
            assertThat(awaitItem().matchItems).isEqualTo(appleMatches.asMatchItems())
        }
    }

    @Test
    fun `saveItem should trigger save item state change`() = runTest {
        // Mock the item to be saved
        val mockItem = MatchItem(id = 1, name = "Apple", category = MarketItemCategory.FOOD)

        // Mock the saveItem call in the use case
        coEvery { itemAddUseCase.saveItem(any()) } returns flowOf(
            ItemAddSaveItemState.Success(
                mockItem.id.toLong()
            )
        )

        // Dispatch the SaveItem event
        viewModel.dispatch(ItemAddEvent.SaveItem(mockItem))

        testDispatcher.scheduler.advanceUntilIdle() // Let the coroutines finish

        viewModel.uiState.test {
            awaitItem()
            assertThat(awaitItem().isSaved).isTrue()
        }
    }

    // TODO Error handling logic improvement

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}