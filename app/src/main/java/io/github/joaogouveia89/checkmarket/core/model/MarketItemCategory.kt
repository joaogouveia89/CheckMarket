package io.github.joaogouveia89.checkmarket.core.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Shower
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.joaogouveia89.checkmarket.R

enum class MarketItemCategory(
    val id: Int,
    @StringRes val nameRes: Int,
    val icon: ImageVector
){
    FOOD(
        id = 0,
        nameRes = R.string.food,
        icon = Icons.Filled.Restaurant
    ),
    SNACKS(
        id = 5,
        nameRes = R.string.snacks,
        icon = Icons.Filled.LocalPizza
    ),
    HYGIENE(
        id = 9,
        nameRes = R.string.hygiene,
        icon = Icons.Filled.Bathtub
    ),
    CLEANING(
        id = 10,
        nameRes = R.string.cleaning,
        icon = Icons.Filled.CleaningServices
    ),

    HOUSEHOLD(
        id = 11,
        nameRes = R.string.household,
        icon = Icons.Filled.Inventory
    ),
}