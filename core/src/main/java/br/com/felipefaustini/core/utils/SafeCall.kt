package br.com.felipefaustini.core.utils

import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T : Any> safeCall(
    coroutineContext: CoroutineContext, call: suspend () -> Result<T>
): Result<T> =
    withContext(coroutineContext) {
        try {
            call.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e.message, e)
        }
    }