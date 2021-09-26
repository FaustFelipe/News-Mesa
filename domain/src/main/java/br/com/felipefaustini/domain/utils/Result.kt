package br.com.felipefaustini.domain.utils

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String?, val throwable: Throwable?): Result<Nothing>()
}