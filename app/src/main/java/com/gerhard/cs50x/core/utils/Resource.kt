package com.gerhard.cs50x.core.utils

/**
 * A generic class that holds the repository's result value with its loading status.
 * @param <T>
 */
sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
