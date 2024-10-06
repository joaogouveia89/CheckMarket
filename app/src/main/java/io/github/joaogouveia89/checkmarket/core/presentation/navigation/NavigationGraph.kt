package io.github.joaogouveia89.checkmarket.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.ItemAddEvent
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.ItemAddScreen
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.ItemAddViewModel
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
            val viewModel: ItemAddViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(uiState.isSaved) {
                if (uiState.isSaved) {
                    navController.popBackStack()
                }
            }

            ItemAddScreen(
                onNavigateBack = { navController.popBackStack() },
                onItemSelect = { item ->
                    if (item.id == -1) {
                        navController.navigate(
                            CreateNewItemNav.CreateNewIScreen.passItemName(
                                itemName = item.name
                            )
                        )
                    } else {
                        viewModel.dispatch(ItemAddEvent.SaveItem(item))
                    }
                },
                onNewQuery = { viewModel.dispatch(ItemAddEvent.UpdateQuery(it)) },
                uiState = uiState
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