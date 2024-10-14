package io.github.joaogouveia89.checkmarket.marketList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.checkmarket.marketList.domain.usecase.MarketListStatus
import io.github.joaogouveia89.checkmarket.marketList.domain.usecase.MarketListUseCase
import io.github.joaogouveia89.checkmarket.marketList.presentation.state.MarketListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketListViewModel @Inject constructor(
    private val marketListUseCase: MarketListUseCase
) : ViewModel() {
    private val fetchState = MutableStateFlow<MarketListStatus>(MarketListStatus.Idle)

    val uiState = fetchState
        .map {
            when (it) {
                is MarketListStatus.Loading -> MarketListUiState(isLoading = true)
                is MarketListStatus.Success -> {
                    MarketListUiState(items = it.items)
                }

                is MarketListStatus.Error -> MarketListUiState(
                    errorRes = it.messageRes,
                    showError = true
                )

                else -> MarketListUiState()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(),
            initialValue = MarketListUiState()
        )

    init {
        viewModelScope.launch {
            fetchState.emitAll(
                marketListUseCase.fetchItems()
            )
        }
    }
}