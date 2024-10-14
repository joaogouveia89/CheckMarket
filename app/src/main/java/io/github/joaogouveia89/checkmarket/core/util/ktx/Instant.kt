package io.github.joaogouveia89.checkmarket.core.util.ktx

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun Instant.toHumanReadable(): String {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val duration = now.toInstant(TimeZone.currentSystemDefault())
        .minus(this)

    val minutes = duration.inWholeMinutes
    val hours = duration.inWholeHours
    val days = duration.inWholeDays

    return when {
        minutes < 1 -> "Just now"
        minutes < 60 -> "$minutes minute${if (minutes != 1L) "s" else ""} ago"
        hours < 24 -> "$hours hour${if (hours != 1L) "s" else ""} ago"
        days < 7 -> "$days day${if (days != 1L) "s" else ""} ago"
        else -> this.toString() // Or any other format for longer durations
    }
}