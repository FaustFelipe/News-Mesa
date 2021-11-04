package br.com.felipefaustini.core.utils

import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
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

fun <Y: Any, T: Any> handleResponseCall(call: Response<Y>, result: (Y) -> T): Result<T> {
    return if (call.isSuccessful) {
        val data = call.body()!!
        val dataFormatted = result.invoke(data)
        Result.Success(dataFormatted)
    } else {
        handleApiCodeException(call.code())
    }
}

internal fun handleApiCodeException(code: Int): Result.Error {
    return when (code) {
        HttpURLConnection.HTTP_UNAUTHORIZED -> Result.Error(ErrorEntity.Unauthorized)
        HttpURLConnection.HTTP_UNAVAILABLE -> Result
            .Error(ErrorEntity.ServiceUnavailable)
        else -> Result.Error(ErrorEntity.Unknown)
    }
}