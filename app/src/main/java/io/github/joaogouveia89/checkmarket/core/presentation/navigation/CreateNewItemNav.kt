package io.github.joaogouveia89.checkmarket.core.presentation.navigation

const val NEW_ITEM_NAME_KEY = "NEW_ITEM_NAME_KEY"

sealed class CreateNewItemNav(val route: String) {
    data object CreateNewIScreen : CreateNewItemNav(
        route = "$CREATE_NEW_ITEM_SCREEN_ROUTE?$NEW_ITEM_NAME_KEY=" +
                "{$NEW_ITEM_NAME_KEY}"
    ) {
        fun passItemName(itemName: String) =
            "$CREATE_NEW_ITEM_SCREEN_ROUTE?$NEW_ITEM_NAME_KEY=$itemName"
    }
}