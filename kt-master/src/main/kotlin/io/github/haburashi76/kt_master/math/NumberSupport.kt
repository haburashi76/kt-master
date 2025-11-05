package io.github.haburashi76.kt_master.math

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.round

/**
 * 비율 계산
 * 10.0.percent(20.0) -> 50.0(%)
 */
fun Number.ratio(target: Number): Double = (this.toDouble() / target.toDouble()) * 100

/**
 * 특정 자리에서 반올림
 */
fun Double.round(decimal: Int = 1): Double {
    val factor = 10.0.pow(decimal)
    return round(this * factor) / factor
}
fun Float.round(decimal: Int = 1): Float = this.toDouble().round(decimal).toFloat()

/**
 * 범위 내에 들어가는지
 */
fun <T> Comparable<T>.isInRange(min: T, max: T) = this >= min && this <= max

/**
 * 절댓값(직접 호출할 수 있게)
 */
fun Int.abs(): Int = abs(this)
fun Double.abs(): Double = abs(this)
fun Long.abs(): Long = abs(this)
fun Float.abs(): Float = abs(this)

/**
 * 선형 보간
 */
fun lerp(min: Double, max: Double, t: Double): Double = min + (max - min) * t
/**
 * 선형 보간
 */
fun lerp(min: Float, max: Float, t: Float): Float = min + (max - min) * t

/**
 * 역보간
 */
fun inverseLerp(a: Double, b: Double, value: Double): Double =
    if (a == b) 0.0 else (value - a) / (b - a)
/**
 * 역보간
 */
fun inverseLerp(a: Float, b: Float, value: Float): Float =
    inverseLerp(a.toDouble(), b.toDouble(), value.toDouble()).toFloat()

/**
 * 비교. 단, 오차 허용 범위 존재
 */
fun Double.equalsWithTolerance(other: Double, tolerance: Double = 1e-9): Boolean =
    abs(this - other) <= tolerance