package io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.checkmarket.core.presentation.navigation.NEW_ITEM_NAME_KEY
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.ItemCreateSaveUiModel
import javax.inject.Inject

internal sealed class ItemCreateEvent{
    data class SaveItem(val item: ItemCreateSaveUiModel): ItemCreateEvent()
}

@HiltViewModel
class ItemCreateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    internal fun dispatch(event: ItemCreateEvent) {
        TODO("Not yet implemented")
    }

    // Public for now
    val itemName = savedStateHandle.get<String>(key = NEW_ITEM_NAME_KEY)
}