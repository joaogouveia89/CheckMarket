package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem

@Composable
fun ItemAddContent(
    modifier: Modifier = Modifier,
    query: String,
    matchItems: List<MatchItem>,
    onItemSelect: (MatchItem) -> Unit
) {
    val contentAlignment = if (query.isEmpty())
        Alignment.Center
    else
        Alignment.TopStart

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = contentAlignment
    ) {
        if (query.isEmpty()) {
            NoQueryTypedMessage()
        } else {
            MatchItemsList(
                matchItems = matchItems,
                onItemSelect = { matchItem ->
                    val item = if (matchItem.id == NEW_ITEM_ID) {
                        matchItem.copy(name = query)
                    } else matchItem

                    onItemSelect(item)
                }
            )
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