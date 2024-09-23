package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem

@Composable
fun MatchItemsList(
    modifier: Modifier = Modifier,
    matchItems: List<MatchItem>,
    isLoading: Boolean,
    onItemSelect: (MatchItem) -> Unit
) {

    val newItem = MatchItem(
        id = NEW_ITEM_ID,
        name = stringResource(id = R.string.new_item)
    )

    println("JOAODEBUG::isLoading = $isLoading")

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        item {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onItemSelect(newItem) },
                verticalAlignment = CenterVertically
            ) {
                Text(
                    text = newItem.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_new_item)
                )
            }

        }

        items(matchItems.size) {
            MatchItem(
                onItemSelect = onItemSelect,
                matches = matchItems,
                it
            )
        }
    }
}

@Composable
private fun MatchItem(
    onItemSelect: (MatchItem) -> Unit,
    matches: List<MatchItem>,
    it: Int
) {
    Text(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onItemSelect(matches[it]) },
        text = matches[it].name,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Preview(showBackground = true)
@Composable
private fun MatchItemsListPreview() {
    MatchItemsList(
        matchItems = listOf(
            MatchItem(
                id = 0,
                name = "Arroz"
            ),
            MatchItem(
                id = 1,
                name = "Feijão"
            ),
            MatchItem(
                id = 2,
                name = "Macarrão"
            )
        ),
        isLoading = false
    ) {}
}