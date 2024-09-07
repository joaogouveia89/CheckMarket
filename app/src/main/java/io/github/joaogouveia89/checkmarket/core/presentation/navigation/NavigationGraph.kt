package io.github.joaogouveia89.checkmarket.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.joaogouveia89.checkmarket.history.presentation.HistoryScreen
import io.github.joaogouveia89.checkmarket.marketList.presentation.MarketListScreen
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.MarketListiTemAddScreen
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.ItemCreateEvent
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.ItemCreateScreen
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.ItemCreateViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MarketList.route
    ) {
        composable(
            BottomNavItem.MarketList.route,
            arguments = listOf(
                navArgument(SCROLL_TO_ITEM_ID) {
                    type = NavType.LongType
                    defaultValue = DEFAULT_SCROLL_TO_ITEM_ID
                }
            )
        ) {
            MarketListScreen(
                onNavigateToAddMarketItemClick = {
                    navController.navigate(MARKET_LIST_ITEM_ADD_SCREEN_ROUTE)
                }
            )
        }

        composable(BottomNavItem.History.route) {
            HistoryScreen()
        }

        composable(MARKET_LIST_ITEM_ADD_SCREEN_ROUTE) {
            val resultList = listOf(
                MatchItem(
                    id = 0,
                    name = "Arroz"
                ),
                MatchItem(
                    id = 1,
                    name = "Feijão"
                ),
                MatchItem(
                    id = 2,
                    name = "Macarrão"
                )
            ) // Temporary, it will come from view model

            MarketListiTemAddScreen(
                onNavigateBack = { navController.popBackStack() },
                onItemSelect = { item ->
                    if (item.id == -1) {
                        navController.navigate(
                            CreateNewItemNav.CreateNewIScreen.passItemName(
                                itemName = item.name
                            )
                        )
                    } else {
                        // TODO: Call view model to save item and navigate back
                        navController.navigate(MARKET_LIST_ITEM_ADD_SCREEN_ROUTE)
                    }
                },
                onNewQuery = { },
                matchItems = resultList
            )
        }

        composable(
            route = CreateNewItemNav.CreateNewIScreen.route,
            arguments = listOf(
                navArgument(NEW_ITEM_NAME_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val viewModel: ItemCreateViewModel = hiltViewModel()

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            ItemCreateScreen(
                modifier = Modifier,
                uiState = uiState,
                onSaveClick = { viewModel.dispatch(ItemCreateEvent.SaveItem(it)) },
                onErrorDismiss = { viewModel.dispatch(ItemCreateEvent.DismissError) },
                onSaveSuccess = { savedItemId ->
                    navController.navigate(
                        MarketListItemNav.CreateMarketListItemScreen.passScrollToId(
                            id = savedItemId
                        )
                    ) {
                        /* popTo function clears the intermediate screens from the
                        * backstack in this case the MarketListItemAddScreenRoute
                        * and CreateNewIScreen the inclusive parameter should be
                        * set to true if you want to remove the BottomNavItem.MarketList first
                         */
                        popUpTo(MARKET_LIST_ROUTE) {
                            inclusive = false
                        }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}