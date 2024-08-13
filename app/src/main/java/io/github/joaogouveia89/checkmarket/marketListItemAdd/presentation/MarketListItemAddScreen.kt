package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.compose.foundation.layout.Box
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
fun MarketListiTemAddScreen(
    onNavigateBack: () -> Unit,
    onItemSelect: (Int) -> Unit,
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

        val contentAlignment = if (query.isEmpty())
            Alignment.Center
        else
            Alignment.TopStart

        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = contentAlignment
        ) {
            if (query.isEmpty()) {
                NoQueryTypedMessage()
            } else {
                MatchItemsList(
                    matchItems = matchItems,
                    onItemSelect = { onItemSelect(it) }
                )
            }
        }
    }
}

@Composable
private fun NoQueryTypedMessage() {
    Text(
        modifier = Modifier
            .padding(horizontal = 80.dp),
        textAlign = TextAlign.Center,
        color = Color.Gray,
        text = stringResource(R.string.no_query_typed_message),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Preview
@Composable
private fun MarketListItemAddScreenPreview() {
    MarketListiTemAddScreen(
        onNavigateBack = {},
        onItemSelect = {},
        matchItems = listOf(),
        onNewQuery = {}
    )
}

