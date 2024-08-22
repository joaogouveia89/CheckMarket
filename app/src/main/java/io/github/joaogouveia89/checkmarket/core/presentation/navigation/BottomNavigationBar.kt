package io.github.joaogouveia89.checkmarket.core.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.joaogouveia89.checkmarket.ui.theme.CheckMarketRed
import io.github.joaogouveia89.checkmarket.ui.theme.CheckMarketRedLight

@Composable
fun BottomNavigationBar(
    navController: NavController,
) {
    val items = listOf(
        BottomNavItem.MarketList,
        BottomNavItem.History,
    )

    NavigationBar(
        containerColor = CheckMarketRed,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { destination ->
            NavigationBarItem(
                selected = currentRoute == destination.route,
                onClick = {
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // the new screen is put on the top of the navigation stack
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = null,
                        tint = if (currentRoute == destination.route) Black else White
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = destination.title),
                        // to avoid text overlapping the icon
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = White
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = CheckMarketRedLight
                )
            )
        }
    }
}

@Composable
fun currentRoute(navHostController: NavHostController): String? {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}