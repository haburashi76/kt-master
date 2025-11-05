package io.github.haburashi76.kt_master

/**
 * 값이 참일 때 람다식 실행. 이후 자기 자신 반환
 */
inline fun Boolean.then(then: () -> Unit): Boolean {
    if (this) then()
    return this
}

/**
 * 값이 거짓일 때 람다식 실행. 이후 자기 자신 반환
 */
inline fun Boolean.however(however: () -> Unit): Boolean {
    if (!this) however()
    return this
}

/**
 * 참일 때 실행
 */
inline fun <T> T.alsoIf(condition: (T) -> Boolean, block: (T) -> Unit): T {
    if (condition(this)) block(this)
    return this
}

/**
 * 거짓일 때 실행
 */
inline fun <T> T.alsoUnless(condition: (T) -> Boolean, block: (T) -> Unit): T {
    if (!condition(this)) block(this)
    return this
}

/**
 * Null일 때 실행
 */
inline fun <T> T?.ifNull(block: () -> Unit): T? {
    if (this == null) block()
    return this
}

/**
 * Null이 아닐 때 실행
 */
inline fun <T> T?.ifNotNull(block: (T) -> Unit): T? {
    if (this != null) block(this)
    return this
}

/**
 * 성공일 때만 실행
 */
inline fun <T> Result<T>.onSuccess(block: (T) -> Unit): Result<T> {
    if (isSuccess) block(getOrThrow())
    return this
}

/**
 * 실패일 때만 실행
 */
inline fun <T> Result<T>.onFailure(block: (Throwable) -> Unit): Result<T> {
    exceptionOrNull()?.let(block)
    return this
}

/**
 * 실패 시 대체 값 제공
 */
inline fun <T> Result<T>.recover(defaultValue: () -> T): T {
    return getOrElse { defaultValue() }
}

/**
 * 성공 시 값 변환
 */
inline fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> {
    return fold(
        onSuccess = { Result.success(transform(it)) },
        onFailure = { Result.failure(it) }
    )
}
