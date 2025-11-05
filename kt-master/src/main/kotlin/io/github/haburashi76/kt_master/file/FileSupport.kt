package io.github.haburashi76.kt_master.file

import java.io.File

/**
 * 없으면 만들기
 */
fun File.ensureExists() {
    if (!exists()) mkdirs()
}

fun File.writeTextIfNotExists(text: String): Pair<Boolean, String> {
    if (!exists()) {
        parentFile?.mkdirs()
        writeText(text)
        return true to readText()
    }
    return false to readText()
}