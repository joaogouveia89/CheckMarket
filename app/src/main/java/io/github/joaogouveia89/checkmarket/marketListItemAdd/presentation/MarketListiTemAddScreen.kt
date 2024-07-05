package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.presentation.CheckMarketAppBar

@Composable
fun MarketListiTemAddScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CheckMarketAppBar(
                title = R.string.market_list_item_add
            )
        }
    ) { paddingValues ->
        MarketListItemAddContent(
            modifier = Modifier
                .fillMaxHeight(),
            paddingValues = paddingValues,
            onNavigateBack = onNavigateBack
        )
    }
}

