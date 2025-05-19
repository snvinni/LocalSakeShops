package com.jetbrains.vini.core

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