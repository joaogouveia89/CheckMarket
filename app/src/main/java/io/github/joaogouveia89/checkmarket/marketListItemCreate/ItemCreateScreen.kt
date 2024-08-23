package io.github.joaogouveia89.checkmarket.marketListItemCreate

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ItemCreateScreen(
    modifier: Modifier = Modifier,
    itemName: String?
) {
    Text(text = itemName ?: "No item name provided")
}