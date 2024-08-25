package io.github.joaogouveia89.checkmarket.marketList.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory

@Composable
fun CategoryMark(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    icon: ImageVector
) {
    Row(
        modifier = modifier,
        verticalAlignment = CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = title),
            tint = Color.Unspecified
        )
        Text(
            modifier = Modifier
                .padding(start = 12.dp),
            text = stringResource(id = title),
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryMarkPreview() {
    CategoryMark(
        title = MarketItemCategory.FOOD.nameRes,
        icon = MarketItemCategory.FOOD.icon
    )
}