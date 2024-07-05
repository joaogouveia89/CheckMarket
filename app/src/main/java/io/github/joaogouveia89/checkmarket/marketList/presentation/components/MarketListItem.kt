package io.github.joaogouveia89.checkmarket.marketList.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.core.model.MarketItem
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.core.util.ktx.asPriceFormat
import io.github.joaogouveia89.checkmarket.core.util.ktx.toHumanReadable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun MarketListItem(
    modifier: Modifier = Modifier,
    marketItem: MarketItem
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            marketItem.let {
                TopInfo(
                    name = it.name,
                )
                BottomInfo(
                    price = it.price,
                    quantity = it.quantity,
                    updatedAt = it.updatedAt.toHumanReadable()
                )
            }
        }
    }
}

@Composable
private fun TopInfo(
    name: String
) {
    Row {
        Text(
            modifier = Modifier.padding(16.dp),
            text = name,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun BottomInfo(
    price: Double?,
    quantity: Int?,
    updatedAt: String
) {
    Row(
        verticalAlignment = CenterVertically
    ) {
        price?.let {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f),
            ) {
                MarketItemIconInfo(
                    info = it.asPriceFormat(),
                    icon = Icons.Outlined.AttachMoney,
                    iconContentDescription = "Price"
                )
            }
        }
        quantity?.let {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f),
            ) {
                MarketItemIconInfo(
                    info = it.toString(),
                    icon = Icons.Outlined.Numbers,
                    iconContentDescription = "Quantity"
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = updatedAt,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Composable
private fun MarketItemIconInfo(
    info: String,
    icon: ImageVector,
    iconContentDescription: String
) {
    Row(
        verticalAlignment = CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconContentDescription
        )
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = info,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketListItemPreview() {
    MarketListItem(
        marketItem = MarketItem(
            id = 1,
            name = "Apple",
            price = 100.0,
            quantity = 3,
            category = MarketItemCategory.FRUITS,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        )
    )
}