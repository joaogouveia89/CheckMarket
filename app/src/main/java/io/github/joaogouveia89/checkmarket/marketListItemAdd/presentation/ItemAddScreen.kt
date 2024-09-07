package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.presentation.topBars.CheckMarketSearchAppBar
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem

internal const val NEW_ITEM_ID = -1

@Composable
fun ItemAddScreen(
    onNavigateBack: () -> Unit,
    onItemSelect: (MatchItem) -> Unit,
    onNewQuery: (String) -> Unit,
    matchItems: List<MatchItem> = emptyList()
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
            query = query,
            matchItems = matchItems,
            onItemSelect = onItemSelect
        )
    }
}



@Preview
@Composable
private fun MarketListItemAddScreenPreview() {
    ItemAddScreen(
        onNavigateBack = {},
        onItemSelect = {},
        matchItems = listOf(),
        onNewQuery = {}
    )
}

