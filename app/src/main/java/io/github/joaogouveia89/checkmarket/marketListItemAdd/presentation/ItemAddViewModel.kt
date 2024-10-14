package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase.ItemAddUseCase
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase.QuerySimilarityEvaluationStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.asItemAddSaveUiModel
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.state.ItemAddContentState
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.state.ItemAddSaveItemState
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.state.ItemAddState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal sealed class ItemAddEvent {
    data class UpdateQuery(val query: String) : ItemAddEvent()
    data class SaveItem(val item: MatchItem) : ItemAddEvent()
    data object DismissError : ItemAddEvent()
}

@HiltViewModel
class ItemAddViewModel @Inject constructor(
    private val itemAddUseCase: ItemAddUseCase
) : ViewModel() {
    private val allItemsFetchState = MutableStateFlow<FetchItemsStatus>(FetchItemsStatus.Idle)
    private val query = MutableStateFlow("")
    private val showErrorBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val querySimilarityEvaluationStatus: MutableStateFlow<QuerySimilarityEvaluationStatus> =
        MutableStateFlow(QuerySimilarityEvaluationStatus.Idle)
    private val itemAddSaveItemState: MutableStateFlow<ItemAddSaveItemState> =
        MutableStateFlow(ItemAddSaveItemState.Idle)


    private val itemsEvaluationState = combine(
        allItemsFetchState,
        query
    ) { fetchState, newQuery ->
        handleFetchItemsAndQueryStates(fetchState, newQuery)
    }

    val uiState: StateFlow<ItemAddState> = combine(
        itemsEvaluationState,
        querySimilarityEvaluationStatus,
        itemAddSaveItemState
    ) { itemsEvaluationState, similarityEvaluationStatus, itemAddSaveItemState ->

        val querySimilarityItemAddState =
            handleQuerySimilarityEvaluationStatus(similarityEvaluationStatus)

        val state = handleItemAddSaveItemState(
            itemAddSaveItemState = itemAddSaveItemState,
            itemAddState = querySimilarityItemAddState ?: itemsEvaluationState
        )

        state
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(),
        initialValue = ItemAddState()
    )

    private fun handleItemAddSaveItemState(
        itemAddSaveItemState: ItemAddSaveItemState,
        itemAddState: ItemAddState
    ): ItemAddState = when (itemAddSaveItemState) {
        is ItemAddSaveItemState.Idle, ItemAddSaveItemState.Loading -> itemAddState
        is ItemAddSaveItemState.Success -> itemAddState.copy(isSaved = true)
        is ItemAddSaveItemState.Error -> itemAddState.copy(
            errorRes = itemAddSaveItemState.messageRes,
            showError = true
        )
    }

    private suspend fun handleFetchItemsAndQueryStates(
        fetchItemsState: FetchItemsStatus,
        newQuery: String
    ) = when (fetchItemsState) {
        is FetchItemsStatus.Loading -> {
            ItemAddState(itemAddItemContentState = ItemAddContentState.LOADING_ALL_ITEMS)
        }

        is FetchItemsStatus.Error -> ItemAddState(
            errorRes = fetchItemsState.messageRes,
            showError = true
        )

        is FetchItemsStatus.OnNewList -> {
            if (newQuery.isNotEmpty()) {
                querySimilarityEvaluationStatus.emitAll(
                    itemAddUseCase.evaluateQuerySimilarity(
                        fetchItemsState.items,
                        newQuery
                    )
                )
                ItemAddState(
                    itemAddItemContentState = ItemAddContentState.LOADING_MATCH_ITEMS
                )
            } else ItemAddState(
                itemAddItemContentState = ItemAddContentState.NO_QUERY_TYPED,
                matchItems = emptyList()
            )

        }

        is FetchItemsStatus.Idle -> ItemAddState()
    }

    private fun handleQuerySimilarityEvaluationStatus(similarityEvaluationStatus: QuerySimilarityEvaluationStatus): ItemAddState? =
        if (query.value.isEmpty()) null
        else when (similarityEvaluationStatus) {
            is QuerySimilarityEvaluationStatus.Idle -> null
            is QuerySimilarityEvaluationStatus.Loading -> ItemAddState(itemAddItemContentState = ItemAddContentState.LOADING_MATCH_ITEMS)
            is QuerySimilarityEvaluationStatus.Success -> {
                ItemAddState(
                    matchItems = similarityEvaluationStatus.items,
                    itemAddItemContentState = ItemAddContentState.MATCH_ITEMS_EVALUATED
                )
            }
        }


    // TODO READ IT https://medium.com/@a.poplawski96/implement-modern-search-functionality-on-android-with-compose-mvvm-clean-architecture-junit5-61cbbee963ba
    // TODO https://maxtayler.medium.com/search-filtering-using-mvvm-kotlin-coroutines-sharedflow-2b2ff587865f

    internal fun dispatch(event: ItemAddEvent) {
        when (event) {
            is ItemAddEvent.UpdateQuery -> updateQuery(event.query)
            is ItemAddEvent.SaveItem -> saveItem(event)
            is ItemAddEvent.DismissError -> {}
        }
    }

    private fun saveItem(event: ItemAddEvent.SaveItem) {
        viewModelScope.launch {
            itemAddSaveItemState.emitAll(itemAddUseCase.saveItem(event.item.asItemAddSaveUiModel()))
        }
    }

    private fun updateQuery(newQuery: String) {
        query.update { newQuery }
    }

    init {
        viewModelScope.launch {
            allItemsFetchState.emitAll(itemAddUseCase.fetchItems())
        }
    }
}
