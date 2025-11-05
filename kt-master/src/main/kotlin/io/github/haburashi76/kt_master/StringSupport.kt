package io.github.haburashi76.kt_master

/**
 * 첫글자를 대문자로
 */
fun String.capitalizeFirst(): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }