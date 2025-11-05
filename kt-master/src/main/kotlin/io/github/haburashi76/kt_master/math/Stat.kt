package io.github.haburashi76.kt_master.math

import kotlin.math.sqrt

/**
 * 평균
 */
fun Iterable<Number>.mean(): Double = if (none()) 0.0 else sumOf { it.toDouble() } / count()

/**
 * 중앙값
 */
fun Iterable<Number>.median(): Double {
    if (none()) return 0.0
    val sorted = map { it.toDouble() }.sorted()
    val mid = count() / 2
    return if (count() % 2 == 0)
        (sorted[mid - 1] + sorted[mid]) / 2
    else
        sorted[mid]
}

/**
 * 최빈값
 */
fun <T> Iterable<T>.mode(): T? =
    groupingBy { it }.eachCount().maxByOrNull { it.value }?.key

/**
 * 분산
 */
fun Iterable<Number>.variance(): Double {
    if (count() <= 1) return 0.0
    val avg = mean()
    return map { (it.toDouble() - avg) * (it.toDouble() - avg) }.mean()
}

/**
 * 표준편차
 */
fun Iterable<Number>.stddev(): Double = sqrt(variance())

/**
 * 가중 평균
 */
fun Iterable<Number>.weightedMean(weights: Iterable<Number>): Double {
    val values = map { it.toDouble() }
    val w = weights.map { it.toDouble() }
    require(values.size == w.size) { "Values and weights must have the same size." }
    val totalWeight = w.sum()
    return if (totalWeight == 0.0) 0.0
    else values.zip(w).sumOf { (v, weight) -> v * weight } / totalWeight
}

/**
 * 비율 정규화(합계 100이 되도록)
 */
fun Iterable<Number>.normalizeWeights(): List<Double> {
    val total = sumOf { it.toDouble() }
    return if (total == 0.0) map { 0.0 } else map { it.toDouble() / total }
}
