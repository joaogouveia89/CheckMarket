package io.github.joaogouveia89.checkmarket.core.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import io.github.joaogouveia89.checkmarket.R

sealed class BottomNavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    data object MarketList : BottomNavItem(
        title = R.string.list,
        icon = R.drawable.ic_cart,
        route = "market_list_route"
    )

    data object History : BottomNavItem(
        title = R.string.history,
        icon = R.drawable.ic_history,
        route = "products_list_route"
    )

}