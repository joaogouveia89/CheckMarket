package io.github.joaogouveia89.checkmarket.core.presentation.navigation

const val NEW_ITEM_NAME_KEY = "NEW_ITEM_NAME_KEY"

sealed class CreateNewItemNav(val route: String) {
    data object CreateNewIScreen : CreateNewItemNav(
        route = "create_new_item_destination?$NEW_ITEM_NAME_KEY=" +
                "{$NEW_ITEM_NAME_KEY}"
    ) {
        fun passItemName(itemName: String) =
            "create_new_item_destination?$NEW_ITEM_NAME_KEY=$itemName"
    }
}