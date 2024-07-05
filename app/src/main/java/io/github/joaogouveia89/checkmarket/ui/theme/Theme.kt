package io.github.joaogouveia89.checkmarket.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


private val LightThemeColors = lightColorScheme(
    primary = CheckMarketPrimary,
    primaryContainer = CheckMarketPrimaryVariant,
    secondary = CheckMarketSecondary,
    secondaryContainer = CheckMarketSecondaryVariant,
    background = CheckMarketBackground,
    surface = CheckMarketSurface,
    onPrimary = CheckMarketOnPrimary,
    onSecondary = CheckMarketOnSecondary,
    onBackground = CheckMarketOnBackground,
    onSurface = CheckMarketOnSurface
)

private val DarkThemeColors = darkColorScheme(
    primary = CheckMarketPrimary,
    primaryContainer = CheckMarketPrimaryVariant,
    secondary = CheckMarketSecondary,
    secondaryContainer = CheckMarketSecondaryVariant,
    background = CheckMarketBackground,
    surface = CheckMarketSurface,
    onPrimary = CheckMarketOnPrimary,
    onSecondary = CheckMarketOnSecondary,
    onBackground = CheckMarketOnBackground,
    onSurface = CheckMarketOnSurface
)

@Composable
fun CheckMarketTheme(
    darkTheme: Boolean = false, // You can toggle this value for dark/light mode
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkThemeColors
    } else {
        LightThemeColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}