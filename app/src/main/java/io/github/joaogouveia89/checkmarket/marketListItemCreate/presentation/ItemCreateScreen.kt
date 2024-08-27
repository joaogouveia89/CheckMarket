package io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.presentation.topBars.CheckMarketAppBar
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.ItemCreateSaveUiModel

@Composable
fun ItemCreateScreen(
    modifier: Modifier = Modifier,
    itemName: String?,
    onNavigateBack: (item: ItemCreateSaveUiModel) -> Unit,
) {
    Scaffold(
        topBar = {
            // Top App Bar
            CheckMarketAppBar(
                title = R.string.item_create_title
            )
        }
    ) { paddingValues ->
        ItemCreateContent(
            modifier = modifier
                .padding(paddingValues),
            itemName = itemName ?: "",
            onSaveClick = onNavigateBack
        )
    }
}