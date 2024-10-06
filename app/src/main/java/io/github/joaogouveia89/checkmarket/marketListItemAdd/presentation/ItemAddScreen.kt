package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.joaogouveia89.checkmarket.core.presentation.topBars.CheckMarketSearchAppBar
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.state.ItemAddState

internal const val NEW_ITEM_ID = -1

@Composable
fun ItemAddScreen(
    onNavigateBack: () -> Unit,
    onNewQuery: (String) -> Unit,
    onItemSelect: (MatchItem) -> Unit,
    uiState: ItemAddState
) {
    var query by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            // Top App Bar
            CheckMarketSearchAppBar(
                onBackClick = { onNavigateBack() },
                onQueryChange = {
                    query = it
                    onNewQuery(query)
                }
            )
        }
    ) { paddingValues ->

        ItemAddContent(
            modifier = Modifier
                .padding(paddingValues),
            itemAddContentState = uiState.itemAddItemContentState,
            matchItems = uiState.matchItems,
            onItemSelect = { matchItem ->
                val item = if (matchItem.id == NEW_ITEM_ID) {
                    matchItem.copy(name = query)
                } else matchItem

                onItemSelect(item)
            }
        )
    }
}


@Preview
@Composable
private fun MarketListItemAddScreenPreview() {
    ItemAddScreen(
        onNavigateBack = {},
        onItemSelect = {},
        uiState = ItemAddState(),
        onNewQuery = {}
    )
}

