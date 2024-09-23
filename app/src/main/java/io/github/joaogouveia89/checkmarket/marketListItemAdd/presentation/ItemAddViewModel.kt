package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase.ItemAddUseCase
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.state.ItemAddState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
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
    private val queryHasChanged = AtomicBoolean(false)

    val uiState: StateFlow<ItemAddState> = combine(
        allItemsFetchState,
        query
    ) { fetchItemsState, newQuery ->
        val state = when (fetchItemsState) {
            is FetchItemsStatus.Loading -> { ItemAddState(isLoading = true) }
            is FetchItemsStatus.Error -> ItemAddState(
                errorRes = fetchItemsState.messageRes,
                showError = true
            )

            is FetchItemsStatus.OnNewList -> {
                val items = if (newQuery.isNotEmpty()) {
                    itemAddUseCase.evaluateQuerySimilarity(fetchItemsState.items, newQuery)
                } else listOf()

                ItemAddState(matchItems = items)
            }

            is FetchItemsStatus.Idle -> ItemAddState()
        }
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


    // TODO READ IT https://medium.com/@a.poplawski96/implement-modern-search-functionality-on-android-with-compose-mvvm-clean-architecture-junit5-61cbbee963ba
    // TODO https://maxtayler.medium.com/search-filtering-using-mvvm-kotlin-coroutines-sharedflow-2b2ff587865f

    internal fun dispatch(event: ItemAddEvent) {
        when (event) {
            is ItemAddEvent.UpdateQuery -> updateQuery(event.query)
            is ItemAddEvent.DismissError -> {}
        }
    }

    private fun updateQuery(newQuery: String) {
        this.query.update { newQuery }
    }

    init {
        viewModelScope.launch {
            allItemsFetchState.emitAll(itemAddUseCase.fetchItems())
        }
    }
}
