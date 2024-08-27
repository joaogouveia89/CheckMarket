package io.github.joaogouveia89.checkmarket.core.presentation.topBars

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.joaogouveia89.checkmarket.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckMarketAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    textColor: Color = White
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = title),
                color = textColor
            )
        },
        colors = checkMarketTopBarColors(),
    )
}

@Preview(showBackground = true)
@Composable
fun CheckMarketAppBarPreview() {
    CheckMarketAppBar(title = R.string.app_name_top_bar_title)
}