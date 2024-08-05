package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.presentation.CheckMarketAppBar

@Composable
fun MarketListiTemAddScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            // Top App Bar
            CheckMarketAppBar(
                title = R.string.add_market_item,
                actionIcon = Icons.Default.Save,
                actionContentDescription = stringResource(R.string.save),
                onActionClick = {},
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

