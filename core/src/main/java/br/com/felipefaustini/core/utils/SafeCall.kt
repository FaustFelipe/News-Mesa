package br.com.felipefaustini.core.utils

import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import kotlin.coroutines.CoroutineContext

suspend fun <T : Any> safeCall(
    coroutineContext: CoroutineContext, call: suspend () -> Result<T>
): Result<T> =
    withContext(coroutineContext) {
        try {
            call.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is IOException -> Result.Error(ErrorEntity.Network)
                is HttpException -> handleApiCodeException(e.code())
                else -> Result.Error(ErrorEntity.Unknown)
            }
        }
    }

internal fun handleApiCodeException(code: Int): Result.Error {
    return when (code) {
        HttpURLConnection.HTTP_NOT_FOUND -> Result.Error(ErrorEntity.NotFound)
        HttpURLConnection.HTTP_FORBIDDEN -> Result.Error(ErrorEntity.AccessDenied)
        HttpURLConnection.HTTP_UNAUTHORIZED -> Result.Error(ErrorEntity.Unauthorized)
        HttpURLConnection.HTTP_UNAVAILABLE -> Result
            .Error(ErrorEntity.ServiceUnavailable)
        else -> Result.Error(ErrorEntity.Unknown)
    }
}