package io.github.joaogouveia89.checkmarket.core.ktx

import io.github.joaogouveia89.checkmarket.core.util.ktx.toHumanReadable
import org.junit.Test

import kotlinx.datetime.*
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class LocalDateTimeExtensionsTest {

    @Test
    fun testJustNow() {
        val now = Clock.System.now()
        assertEquals("Just now", now.toHumanReadable())
    }

    @Test
    fun testOneMinuteAgo() {
        val now = Clock.System.now()
        val oneMinuteAgo = now.minus(1.minutes)
        assertEquals("1 minute ago", oneMinuteAgo.toHumanReadable())
    }

    @Test
    fun testMultipleMinutesAgo() {
        val now = Clock.System.now()
        val fiveMinutesAgo = now.minus(5.minutes)
        assertEquals("5 minutes ago", fiveMinutesAgo.toHumanReadable())
    }

    @Test
    fun testOneHourAgo() {
        val now = Clock.System.now()
        val oneHourAgo = now.minus(1.hours)
        assertEquals("1 hour ago", oneHourAgo.toHumanReadable())
    }

    @Test
    fun testMultipleHoursAgo() {
        val now = Clock.System.now()
        val fiveHoursAgo = now.minus(5.hours)
        assertEquals("5 hours ago", fiveHoursAgo.toHumanReadable())
    }

    @Test
    fun testOneDayAgo() {
        val now = Clock.System.now()
        val oneDayAgo = now.minus(1.days)
        assertEquals("1 day ago", oneDayAgo.toHumanReadable())
    }

    @Test
    fun testMultipleDaysAgo() {
        val now = Clock.System.now()
        val threeDaysAgo = now.minus(3.days)
        assertEquals("3 days ago", threeDaysAgo.toHumanReadable())
    }

    @Test
    fun testMoreThanAWeekAgo() {
        val now = Clock.System.now()
        val tenDaysAgo = now.minus(10.days)
        assertEquals(tenDaysAgo.toString(), tenDaysAgo.toHumanReadable())
    }

    @Test
    fun testOneWeekAgo() {
        val now = Clock.System.now()
        val oneWeekAgo = now.minus(7.days)
        assertEquals(oneWeekAgo.toString(), oneWeekAgo.toHumanReadable())
    }
}
