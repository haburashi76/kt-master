package io.github.haburashi76.kt_master

import java.time.LocalDate

val now
    get() = System.currentTimeMillis()

val today: LocalDate
    get() = LocalDate.now()

fun formatDuration(ms: Long): String {
    var seconds = ms / 1000
    val hours = seconds / 3600
    seconds %= 3600
    val minutes = seconds / 60
    seconds %= 60

    return buildString {
        if (hours > 0) append("${hours}h ")
        if (minutes > 0) append("${minutes}m ")
        append("${seconds}s")
    }.trim()
}

/**
 * 남은 시간 계산기
 */
fun remainingTime(endTime: Long): Long = (endTime - now).coerceAtLeast(0)

/**
 * Cooldown 관리 클래스
 */
class Cooldown(private val durationMs: Long) {
    private val lastUsed = mutableMapOf<Any, Long>()

    fun ready(key: Any): Boolean {
        val last = lastUsed[key] ?: return true
        return now - last >= durationMs
    }

    fun trigger(key: Any) {
        lastUsed[key] = now
    }

    fun remaining(key: Any): Long {
        val last = lastUsed[key] ?: return 0
        return remainingTime(last + durationMs)
    }
}
