package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.ItemAddStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase.ItemAddUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemAddViewModel @Inject constructor(
    private val itemAddUseCase: ItemAddUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            itemAddUseCase
                .fetchItems()
                .collectLatest {
                    handleFetchResponse(it)
                }
        }
    }

    private fun handleFetchResponse(status: ItemAddStatus) {
        when (status) {
            is ItemAddStatus.Success<*> -> {
                val data = status.data
                if (data is List<*>) {
                    // for cast safety
                    val marketItems = data.filterIsInstance<MarketItem>()
                    marketItems.forEach {
                        Log.i("ItemAddViewModel", "handleFetchResponse: $it")
                    }
                }
            }

            is ItemAddStatus.Error -> {

            }

            is ItemAddStatus.Loading -> {

            }
        }
    }
}