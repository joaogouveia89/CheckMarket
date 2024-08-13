package io.github.joaogouveia89.checkmarket.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.joaogouveia89.checkmarket.history.presentation.HistoryScreen
import io.github.joaogouveia89.checkmarket.marketList.presentation.MarketListScreen
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem
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
                onItemSelect = { },
                onNewQuery = { },
                matchItems = resultList
            )
        }
    }
}