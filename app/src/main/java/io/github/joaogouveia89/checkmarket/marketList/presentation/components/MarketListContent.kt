package io.github.joaogouveia89.checkmarket.marketList.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.core.util.PreviewData

@Composable
fun MarketListContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    marketItemsList: Map<MarketItemCategory, List<MarketItem>>,
    isLoading: Boolean,
    onItemClick: (MarketItemCategory, itemIndex: Int) -> Unit,
    onNavigateToAddMarketItemClick: () -> Unit
) {
    val localDensity = LocalDensity.current

    var fabInitX by remember {
        mutableStateOf(0.dp)
    }

    var screenWidth by remember {
        mutableStateOf(0.dp)
    }

    Box(
        modifier = modifier
            .padding(paddingValues)
            .onGloballyPositioned { coordinates ->
                screenWidth = with(localDensity) { coordinates.size.width.toDp() }
            }
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            if (marketItemsList.keys.isEmpty()) {
                ItemAddMessage(
                    paddingEnd = screenWidth - fabInitX + 40.dp
                )
            } else {
                LazyColumn {
                    marketItemsList.keys.forEach { category ->
                        item {
                            CategoryMark(
                                modifier = Modifier.padding(vertical = 12.dp),
                                title = category.nameRes,
                                icon = category.icon
                            )
                        }
                        val categoryItems = marketItemsList[category]
                        items(categoryItems!!.size) { index ->
                            MarketListItem(
                                modifier = Modifier
                                    .clickable { onItemClick(category, index) },
                                marketItem = categoryItems[index]
                            )
                        }
                    }
                }
            }

            ExtendedFloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .onGloballyPositioned { coordinates ->
                        fabInitX = with(localDensity) { coordinates.positionInRoot().x.toDp() }
                    },
                onClick = { onNavigateToAddMarketItemClick() },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.add_market_item_descr),
                        tint = Color.White
                    )
                },
                text = {
                    Text(
                        text = stringResource(id = R.string.add_market_item),
                        color = Color.White
                    )
                },
            )
        }
    }
}

@Composable
private fun ItemAddMessage(
    paddingEnd: Dp
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = paddingEnd, bottom = 12.dp, top = 12.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = stringResource(R.string.start_here),
            style = MaterialTheme.typography.headlineLarge
        )
        Image(
            modifier = Modifier
                .padding(start = 12.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.curved_arrow),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketListContentPreview() {

    val listGroupped = PreviewData.marketItemsList.groupBy { it.category }

    MarketListContent(
        marketItemsList = listGroupped,
        isLoading = false,
        paddingValues = PaddingValues(16.dp),
        onItemClick = { _, _ -> },
        onNavigateToAddMarketItemClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun MarketListContentLoadingPreview() {
    MarketListContent(
        marketItemsList = mapOf(),
        isLoading = true,
        paddingValues = PaddingValues(16.dp),
        onItemClick = { _, _ -> },
        onNavigateToAddMarketItemClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun MarketListContentEmptyListPreview() {
    MarketListContent(
        marketItemsList = mapOf(),
        isLoading = false,
        paddingValues = PaddingValues(16.dp),
        onItemClick = { _, _ -> },
        onNavigateToAddMarketItemClick = {}
    )
}