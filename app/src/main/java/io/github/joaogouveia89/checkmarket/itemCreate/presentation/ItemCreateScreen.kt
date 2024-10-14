package io.github.joaogouveia89.checkmarket.itemCreate.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.presentation.topBars.CheckMarketAppBar
import io.github.joaogouveia89.checkmarket.itemCreate.presentation.model.ItemCreateSaveUiModel
import io.github.joaogouveia89.checkmarket.itemCreate.presentation.state.ItemCreateState

@Composable
fun ItemCreateScreen(
    modifier: Modifier = Modifier,
    uiState: ItemCreateState,
    onSaveClick: (item: ItemCreateSaveUiModel) -> Unit,
    onErrorDismiss: () -> Unit,
    onSaveSuccess: (savedItemId: Long) -> Unit,
    onBackClick: () -> Unit
) {
    // LaunchedEffect ensuress to handle isSaved only once avoiding multiples navigations
    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) {
            onSaveSuccess(uiState.item.id)
        }
    }

    Scaffold(
        topBar = {
            // Top App Bar
            CheckMarketAppBar(
                title = R.string.item_create_title,
                backAction = onBackClick
            )
        }
    ) { paddingValues ->
        ItemCreateContent(
            modifier = modifier
                .padding(paddingValues),
            uiState = uiState,
            onSaveClick = onSaveClick,
            onErrorDismiss = onErrorDismiss
        )
    }
}