package io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.checkmarket.core.presentation.navigation.NEW_ITEM_NAME_KEY
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.ItemCreateSaveUiModel
import javax.inject.Inject

internal enum class ItemCreateFields{
    NAME,
    PRICE,
    QUANTITY,
    CATEGORY
}

internal sealed class ItemCreateEvent{
    data class SaveItem(val item: ItemCreateSaveUiModel): ItemCreateEvent()
}

internal sealed class ItemCreateOutput{
    data class ItemCreatedSuccess(val item: ItemCreateSaveUiModel): ItemCreateOutput()
    data class ItemCreatedError(val fields: List<ItemCreateFields>): ItemCreateOutput()
    data object ItemCreatingLoading: ItemCreateOutput()
}

@HiltViewModel
class ItemCreateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    internal fun dispatch(event: ItemCreateEvent) {
        when(event){
            is ItemCreateEvent.SaveItem -> saveItem(event.item)
        }
    }

    private fun saveItem(item: ItemCreateSaveUiModel) {
        TODO("Not yet implemented")
    }

    // Public for now
    val itemName = savedStateHandle.get<String>(key = NEW_ITEM_NAME_KEY)
}