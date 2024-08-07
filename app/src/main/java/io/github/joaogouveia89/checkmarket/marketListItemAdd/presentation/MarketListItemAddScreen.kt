package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import io.github.joaogouveia89.checkmarket.core.presentation.topBars.CheckMarketSearchAppBar

@Composable
fun MarketListiTemAddScreen(
    onNavigateBack: () -> Unit
) {
    var query = "" // TEMPORARY WHILE THE VIEW MODEL IS NOT IMPLEMENTED
    // TODO: ADD A BACK FUNCTIONALITY TO THE APPBAR
    Scaffold(
        topBar = {
            // Top App Bar
            CheckMarketSearchAppBar(
                onBackClick = { onNavigateBack() },
                onQueryChange = {/* TODO Call view model */ }
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
//            MarketListItemAddContent(
//                modifier = Modifier
//                    .fillMaxHeight(),
//                paddingValues = paddingValues,
//                onNavigateBack = onNavigateBack
//            )
            if (query.isEmpty()) {
                NoQueryTypedMessage()
            }
        }
    }
}

@Composable
fun NoQueryTypedMessage() {
    Text(
        color = Color.Black,
        text = "Use the search bar above to look for an item to add"
    )
}

@Preview
@Composable
private fun MarketListItemAddScreenPreview() {
    MarketListiTemAddScreen {}
}

