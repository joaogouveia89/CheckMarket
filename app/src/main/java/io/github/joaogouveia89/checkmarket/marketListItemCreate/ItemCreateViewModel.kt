package io.github.joaogouveia89.checkmarket.marketListItemCreate

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.checkmarket.core.presentation.navigation.NEW_ITEM_NAME_KEY
import javax.inject.Inject

@HiltViewModel
class ItemCreateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    // Public for now
    val itemName = savedStateHandle.get<String>(key = NEW_ITEM_NAME_KEY)
}