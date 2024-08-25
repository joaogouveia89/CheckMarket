package io.github.joaogouveia89.checkmarket.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.joaogouveia89.checkmarket.core.presentation.navigation.NavigationGraph

@Composable
fun MainScreen(navController: NavHostController) {
    Box(modifier = Modifier) {
        NavigationGraph(navController = navController)
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}