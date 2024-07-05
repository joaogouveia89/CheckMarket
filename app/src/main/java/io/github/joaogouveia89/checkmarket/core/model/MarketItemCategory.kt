package io.github.joaogouveia89.checkmarket.core.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import io.github.joaogouveia89.checkmarket.R

enum class MarketItemCategory(
    val id: Int,
    @StringRes val nameRes: Int,
    @DrawableRes val iconRes: Int
){
    FRUITS(
        id = 0,
        nameRes = R.string.fruits,
        iconRes = R.drawable.ic_fruit
    ),
    CEREALS(
        id = 1,
        nameRes = R.string.cereals,
        iconRes = R.drawable.ic_rice_bowl
    ),
    MEAT(
        id = 2,
        nameRes = R.string.meat,
        iconRes = R.drawable.ic_meat
    ),
    VEGETABLES(
        id = 3,
        nameRes = R.string.vegetables,
        iconRes = R.drawable.ic_vegetables
    ),
    // laticinios
    DAIRY(
        id = 4,
        nameRes = R.string.dairy,
        iconRes = R.drawable.ic_milk
    ),
    SNACKS(
        id = 5,
        nameRes = R.string.snacks,
        iconRes = R.drawable.ic_snacks
    ),
    BREAD(
        id = 6,
        nameRes = R.string.bread,
        iconRes = R.drawable.ic_bread
    ),
    GENERAL_FOOD(
        id = 7,
        nameRes = R.string.general_food,
        iconRes = R.drawable.ic_general_food
    ),

    RESTROOM(
        id = 8,
        nameRes = R.string.restroom,
        iconRes = R.drawable.ic_restroom
    ),

    KITCHEN(
        id = 9,
        nameRes = R.string.kitchen,
        iconRes = R.drawable.ic_kitchen
    ),

    CLEANING(
        id = 10,
        nameRes = R.string.cleaning,
        iconRes = R.drawable.ic_cleaning
    ),

    HOUSEHOLD(
        id = 11,
        nameRes = R.string.household,
        iconRes = R.drawable.ic_household
    ),
}