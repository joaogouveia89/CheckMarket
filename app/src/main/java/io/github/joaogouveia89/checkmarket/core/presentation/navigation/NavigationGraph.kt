package io.github.joaogouveia89.checkmarket.core.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                onItemSelect = { item ->
                    if(item.id == -1){
                        navController.navigate(CreateNewItemNav.CreateNewIScreen.passItemName(itemName = item.name))
                    }else{
                        // TODO: Call view model to save item and navigate back
                        navController.navigate(MarketListItemAddScreenRoute.route)
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
        ){
            // TODO call new item screen and add the item name passed by argument to the item name
        }
    }
}