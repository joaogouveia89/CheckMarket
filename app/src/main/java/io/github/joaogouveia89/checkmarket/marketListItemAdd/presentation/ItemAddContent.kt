package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.state.ItemAddContentState

@Composable
fun ItemAddContent(
    modifier: Modifier = Modifier,
    itemAddContentState: ItemAddContentState,
    matchItems: List<MatchItem>,
    onItemSelect: (MatchItem) -> Unit
) {
    val contentAlignment = if (
        itemAddContentState == ItemAddContentState.NO_QUERY_TYPED ||
        itemAddContentState == ItemAddContentState.LOADING_ALL_ITEMS
    )
        Alignment.Center
    else
        Alignment.TopStart

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = contentAlignment
    ) {

        when (itemAddContentState) {
            ItemAddContentState.NO_QUERY_TYPED -> {
                NoQueryTypedMessage()
            }
            ItemAddContentState.LOADING_MATCH_ITEMS, ItemAddContentState.MATCH_ITEMS_EVALUATED -> {
                MatchItemsList(
                    matchItems = matchItems,
                    isLoading = itemAddContentState == ItemAddContentState.LOADING_MATCH_ITEMS,
                    onItemSelect = onItemSelect
                )
            }
            else -> {
                CircularProgressIndicator()
            }
        }
    }
}

private val sampleMatchItems = listOf( // Sample MatchItems for previews
    MatchItem(id = 1, name = "Apple"),
    MatchItem(id = 2, name = "Banana"),
    MatchItem(id = 3, name = "Orange")
)

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

@Preview(showBackground = true)
@Composable
fun PreviewNoQueryTyped() {
    ItemAddContent(
        modifier = Modifier,
        itemAddContentState = ItemAddContentState.NO_QUERY_TYPED,
        matchItems = emptyList(),
        onItemSelect = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingAllItems() {
    ItemAddContent(
        modifier = Modifier,
        itemAddContentState = ItemAddContentState.LOADING_ALL_ITEMS,
        matchItems = emptyList(),
        onItemSelect = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingMatchItems() {
    ItemAddContent(
        modifier = Modifier,
        itemAddContentState = ItemAddContentState.LOADING_MATCH_ITEMS,
        matchItems = sampleMatchItems, // This will not be shown as we are simulating loading
        onItemSelect = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMatchItemsEvaluated() {
    ItemAddContent(
        modifier = Modifier,
        itemAddContentState = ItemAddContentState.MATCH_ITEMS_EVALUATED,
        matchItems = sampleMatchItems, // Display the evaluated items
        onItemSelect = {}
    )
}