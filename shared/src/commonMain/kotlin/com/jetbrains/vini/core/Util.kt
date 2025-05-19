package com.jetbrains.vini.core

/**
 * A simple sealed wrapper for operations that can succeed or fail:
 * • Resource.Result.Success<T>(data) holds a successful value
 * • Resource.Result.Failure<E>(error) holds an error
 *
 * Helpers:
 * - toResource(): turns a runCatching<T> into Resource.Result<T, Throwable>
 * - mapSuccess(transform): applies `transform` on the success data
 * - mapError(transform): applies `transform` on the error
 */
sealed class Resource<out T, out E> {

    sealed class Result<out T, out E> : Resource<T, E>() {

        data class Success<T>(val data: T) : Result<T, Nothing>()

        data class Failure<E>(val error: E) : Result<Nothing, E>()
    }
}

fun <T> Result<T>.toResource(): Resource.Result<T, Throwable> {
    return Resource.Result.Success(
        getOrElse {
            return Resource.Result.Failure(it)
        }
    )
}

fun <T, E, T2> Resource.Result<T, E>.mapSuccess(
    transform: (T) -> T2
) = when (this) {
    is Resource.Result.Failure -> Resource.Result.Failure(error)
    is Resource.Result.Success -> Resource.Result.Success(transform(data))
}

inline fun <T, E, E2> Resource.Result<T, E>.mapError(
    transform: (E) -> E2
) = when (this) {
    is Resource.Result.Failure -> Resource.Result.Failure(transform(error))
    is Resource.Result.Success -> Resource.Result.Success(data)
}