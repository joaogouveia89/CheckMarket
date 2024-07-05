package io.github.joaogouveia89.checkmarket.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.joaogouveia89.checkmarket.history.presentation.HistoryScreen
import io.github.joaogouveia89.checkmarket.marketList.presentation.MarketListScreen
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.MarketListiTemAddScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MarketList.route
    ) {
        composable(BottomNavItem.MarketList.route) {
            MarketListScreen(
                onNavigateToAddMarketItemClick = {
                    navController.navigate(MarketListItemAddScreenRoute.route)
                }
            )
        }

        composable(BottomNavItem.History.route) {
            HistoryScreen()
        }

        composable(MarketListItemAddScreenRoute.route) {
            MarketListiTemAddScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}