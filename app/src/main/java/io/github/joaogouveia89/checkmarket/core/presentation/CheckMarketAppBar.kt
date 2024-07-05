package io.github.joaogouveia89.checkmarket.core.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.ui.theme.CheckMarketRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckMarketAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    textColor: Color = White,
    backgroundColor: Color = CheckMarketRed,
    actionIcon: ImageVector? = null,
    actionContentDescription: String = "",
    onActionClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = title),
                color = textColor
            )
        },
        colors = topAppBarColors(
            containerColor = backgroundColor,
            actionIconContentColor = textColor,
            navigationIconContentColor = textColor,
            titleContentColor = textColor,
            scrolledContainerColor = backgroundColor
        ),
        actions = {
            actionIcon?.let {icon ->
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { onActionClick() },
                    imageVector = icon,
                    contentDescription = actionContentDescription
                )
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun CheckMarketAppBarPreview() {
    CheckMarketAppBar(title = R.string.app_name_top_bar_title)
}