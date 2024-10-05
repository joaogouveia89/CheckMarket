package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase.ItemAddUseCase
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase.QuerySimilarityEvaluationStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.state.ItemAddContentState
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

    val uiState: StateFlow<ItemAddState> = combine(
        allItemsFetchState,
        querySimilarityEvaluationStatus,
        query
    ) { fetchItemsState, similarityEvaluationStatus, newQuery ->

        val querySimilarityItemAddState =
            handleQuerySimilarityEvaluationStatus(similarityEvaluationStatus)
        val fetchItemsAndQueryStates = handleFetchItemsAndQueryStates(fetchItemsState, newQuery)

        val state = querySimilarityItemAddState ?: fetchItemsAndQueryStates

        if (state.showError && !showErrorBar.value) {
            state.copy(showError = false)
        } else {
            state
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(),
        initialValue = ItemAddState()
    )

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
                itemAddItemContentState = ItemAddContentState.NO_QUERY_TYPED
            )

        }

        is FetchItemsStatus.Idle -> ItemAddState()
    }

    private fun handleQuerySimilarityEvaluationStatus(similarityEvaluationStatus: QuerySimilarityEvaluationStatus): ItemAddState? =
        when (similarityEvaluationStatus) {
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
            is ItemAddEvent.DismissError -> {}
        }
    }

    private fun updateQuery(newQuery: String) {
        query.update { newQuery }
        querySimilarityEvaluationStatus.update { QuerySimilarityEvaluationStatus.Idle }
    }

    init {
        viewModelScope.launch {
            allItemsFetchState.emitAll(itemAddUseCase.fetchItems())
        }
    }
}
