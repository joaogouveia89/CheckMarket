package io.github.joaogouveia89.checkmarket.core.util.ktx

fun String.capitalizeFirstLetters(): String =
    split(" ").joinToString(separator = " ") { word -> word.replaceFirstChar { letter -> letter.uppercase() } }