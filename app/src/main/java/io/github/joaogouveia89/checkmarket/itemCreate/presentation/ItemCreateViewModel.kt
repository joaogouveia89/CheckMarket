package io.github.joaogouveia89.checkmarket.itemCreate.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.checkmarket.core.presentation.navigation.NEW_ITEM_NAME_KEY
import io.github.joaogouveia89.checkmarket.itemCreate.domain.repository.ItemCreateStatus
import io.github.joaogouveia89.checkmarket.itemCreate.domain.usecase.ItemCreateUseCase
import io.github.joaogouveia89.checkmarket.itemCreate.presentation.model.ItemCreateSaveUiModel
import io.github.joaogouveia89.checkmarket.itemCreate.presentation.state.ItemCreateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
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

    private val saveItemState = MutableStateFlow<ItemCreateStatus>(ItemCreateStatus.Idle)

    private val showErrorBar: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState: StateFlow<ItemCreateState> = combine(
        saveItemState,
        showErrorBar
    ) { saveItemState, showErrorBar ->
        val state = when (saveItemState) {
            is ItemCreateStatus.Loading -> ItemCreateState(isLoading = true)
            is ItemCreateStatus.Error -> ItemCreateState(
                errorRes = saveItemState.messageRes,
                showError = true
            )

            is ItemCreateStatus.Success -> ItemCreateState(isSaved = true)
            is ItemCreateStatus.ValidationErrors -> ItemCreateState(invalidFields = saveItemState.invalidFields)
            is ItemCreateStatus.Idle -> ItemCreateState()
        }
        if (state.showError && !showErrorBar) {
            state.copy(showError = false)
        } else {
            state
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(),
        initialValue = ItemCreateState(
            item = ItemCreateSaveUiModel(
                name = savedStateHandle.get<String>(key = NEW_ITEM_NAME_KEY) ?: ""
            )
        )
    )


    internal fun dispatch(event: ItemCreateEvent) {
        when (event) {
            is ItemCreateEvent.SaveItem -> saveItem(event.item)
            is ItemCreateEvent.DismissError -> showErrorBar.update { false }
        }
    }

    private fun saveItem(item: ItemCreateSaveUiModel) {
        viewModelScope.launch {
            saveItemState.emitAll(itemCreateUseCase.saveItem(item))
        }
    }
}