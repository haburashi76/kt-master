package io.github.haburashi76.kt_master.math.random

import kotlin.random.Random
import kotlin.random.Random.Default.nextDouble

fun chance(prob: Number): Boolean = Random.nextDouble() < prob.toDouble()

fun chanceByPercent(prob: Number): Boolean = Random.nextDouble(0.0, 100.0) < prob.toDouble()

fun random(min: Int, max: Int) = (min..max).random()

fun random(min: Long, max: Long) = (min..max).random()

fun random(min: Short, max: Short) = (min..max).random()

fun random(min: Byte, max: Byte) = (min..max).random()

fun random(min: UInt, max: UInt) = (min..max).random()

fun random(min: ULong, max: ULong) = (min..max).random()

fun random(min: UShort, max: UShort) = (min..max).random()

fun random(min: UByte, max: UByte) = (min..max).random()

fun random(min: Float, max: Float) = Random.nextDouble(min.toDouble(), max.toDouble()).toFloat()

fun random(min: Double, max: Double) = Random.nextDouble(min, max)

/**
 * 가중치 랜덤 선택
 */
fun <T> weightedRandom(items: List<T>, weights: List<Number>): T? {
    if (items.isEmpty() || weights.isEmpty()) return null
    require(items.size == weights.size) { "Items and weights must have the same size." }

    val r = nextDouble(weights.sumOf { it.toDouble() })
    var cumulative = 0.0

    for (i in items.indices) {
        cumulative += weights[i].toDouble()
        if (r <= cumulative) return items[i]
    }
    return items.last()
}