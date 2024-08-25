package io.github.joaogouveia89.checkmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.joaogouveia89.checkmarket.core.presentation.MainScreen
import io.github.joaogouveia89.checkmarket.ui.theme.CheckMarketTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckMarketTheme {
                MainScreen(navController = rememberNavController())
            }
        }
    }
}