package io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.checkmarket.core.presentation.navigation.NEW_ITEM_NAME_KEY
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateStatus
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.usecase.ItemCreateUseCase
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.ItemCreateSaveUiModel
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.state.ItemCreateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal sealed class ItemCreateEvent {
    data class SaveItem(val item: ItemCreateSaveUiModel) : ItemCreateEvent()
    data object DismissError : ItemCreateEvent()
}

@HiltViewModel
class ItemCreateViewModel @Inject constructor(
    private val itemCreateUseCase: ItemCreateUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState = MutableStateFlow(ItemCreateState())
        private set

    internal fun dispatch(event: ItemCreateEvent) {
        when (event) {
            is ItemCreateEvent.SaveItem -> saveItem(event.item)
            is ItemCreateEvent.DismissError -> dismissErrorMessage()
        }
    }

    private fun saveItem(item: ItemCreateSaveUiModel) {
        viewModelScope.launch {
            itemCreateUseCase.saveItem(item)
                .collectLatest { handleResponse(it) }
        }
    }

    private fun dismissErrorMessage() {
        uiState.update {
            it.copy(
                errorRes = null
            )
        }
    }

    private fun handleResponse(itemCreateStatus: ItemCreateStatus) {
        when (itemCreateStatus) {
            is ItemCreateStatus.Error -> {
                // creating a new object here because I don't want to know the previous state
                uiState.update {
                    ItemCreateState(
                        errorRes = itemCreateStatus.messageRes,
                        item = it.item
                    )
                }
            }

            is ItemCreateStatus.Success -> {
                uiState.update {
                    ItemCreateState(
                        isSaved = true,
                        item = it.item.copy(
                            id = itemCreateStatus.id
                        )
                    )
                }
            }

            is ItemCreateStatus.Loading -> {
                uiState.update {
                    ItemCreateState(
                        isLoading = true,
                        item = it.item
                    )
                }
            }

            is ItemCreateStatus.ValidationErrors -> {
                uiState.update {
                    ItemCreateState(
                        invalidFields = itemCreateStatus.invalidFields,
                        item = it.item
                    )
                }
            }
        }
    }

    init {
        uiState.update {
            it.copy(
                item = it.item.copy(
                    name = savedStateHandle.get<String>(key = NEW_ITEM_NAME_KEY) ?: ""
                )
            )
        }
    }
}