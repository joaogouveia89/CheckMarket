package io.github.joaogouveia89.checkmarket.core.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.joaogouveia89.checkmarket.R

sealed class BottomNavItem(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    data object MarketList : BottomNavItem(
        title = R.string.list,
        icon = Icons.Filled.ShoppingCart,
        route = "market_list_route"
    )

    data object History : BottomNavItem(
        title = R.string.history,
        icon = Icons.Filled.History,
        route = "products_list_route"
    )
}