package io.github.joaogouveia89.checkmarket.itemAdd

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import io.github.joaogouveia89.checkmarket.MainDispatcherRule
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.marketListItemAdd.data.usecase.ItemAddUseCaseImpl
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.ItemAddRepository
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase.ItemAddUseCase
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase.QuerySimilarityEvaluationStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.ItemAddEvent
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.ItemAddViewModel
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.state.ItemAddContentState
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.state.ItemAddSaveItemState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineScope
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

    private val appleMatches = listOf(MatchItem(id = 1, name = "Apple", category = MarketItemCategory.FOOD))

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

    // test not working
    @Test
    fun `query update should trigger the similarity evaluation`() = runTest {
        // Mock the saveItem call in the use case

        val newQuery = "Apple"

        coEvery { itemAddUseCase.evaluateQuerySimilarity(any(), any()) } returns flowOf(QuerySimilarityEvaluationStatus.Success(
            appleMatches
        ))

        viewModel.dispatch(ItemAddEvent.UpdateQuery(newQuery))

        testDispatcher.scheduler.advanceUntilIdle() // Let the coroutines finish
        viewModel.uiState.test {
            awaitItem()
            assertThat(awaitItem().matchItems).isEqualTo(appleMatches)
        }
    }

//    @Test
//    fun `saveItem should trigger save item state change`() = runTest {
//        // Mock the item to be saved
//        val mockItem = MatchItem(id = 1, name = "Apple", category = MarketItemCategory.FOOD)
//
//        // Mock the saveItem call in the use case
//        coEvery { itemAddUseCase.saveItem(any()) } returns flowOf(ItemAddSaveItemState.Success(mockItem.id.toLong()))
//
//        // Dispatch the SaveItem event
//        viewModel.dispatch(ItemAddEvent.SaveItem(mockItem))
//
//        testDispatcher.scheduler.advanceUntilIdle() // Let the coroutines finish
//
//        // Check that the state reflects a successful save
//        assertThat(viewModel.uiState.value.isSaved).isTrue()
//    }
//
//    @Test
//    fun `fetchItems should handle errors during fetching`() = runTest {
//        // Mock the error state in fetchItems
//        coEvery { itemAddUseCase.fetchItems() } returns flowOf(FetchItemsStatus.Error(messageRes = 123))
//
//        viewModel = ItemAddViewModel(itemAddUseCase)
//
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Verify error state
//        assertThat(viewModel.uiState.value.showError).isTrue()
//        assertThat(viewModel.uiState.value.errorRes).isEqualTo(123)
//    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}