package io.github.joaogouveia89.checkmarket.core.util.ktx

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.toHumanReadable(): String{
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val duration = Instant.fromEpochMilliseconds(this.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds())
        .minus(Instant.fromEpochMilliseconds(now.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()))

    return when {
        duration.inWholeMinutes < 1 -> "Just now"
        duration.inWholeMinutes < 60 -> "${duration.inWholeMinutes} minute${if (duration.inWholeMinutes > 1) "s" else ""} ago"
        duration.inWholeHours < 24 -> "${duration.inWholeHours} hour${if (duration.inWholeHours > 1) "s" else ""} ago"
        duration.inWholeDays < 7 -> "${duration.inWholeDays} day${if (duration.inWholeDays > 1) "s" else ""} ago"
        else -> this.date.toString() // Or any other format for longer durations
    }
}