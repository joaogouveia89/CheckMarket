package io.github.joaogouveia89.checkmarket.core.presentation.navigation

const val SCROLL_TO_ITEM_ID = "SCROLL_TO_ITEM_ID"
const val DEFAULT_SCROLL_TO_ITEM_ID = -1L

sealed class MarketListItemNav(val route: String) {
    data object CreateMarketListItemScreen : CreateNewItemNav(
        route = "$MARKET_LIST_ROUTE?$SCROLL_TO_ITEM_ID=" +
                "{$SCROLL_TO_ITEM_ID}"
    ) {
        fun passScrollToId(id: Long) =
            "$MARKET_LIST_ROUTE?$SCROLL_TO_ITEM_ID=$id"
    }
}