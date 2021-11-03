package br.com.felipefaustini.core.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        when(response.code) {
            401 -> TODO("Show unathorized error message")
        }
        return response
    }

}