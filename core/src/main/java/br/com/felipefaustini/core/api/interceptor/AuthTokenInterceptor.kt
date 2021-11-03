package br.com.felipefaustini.core.api.interceptor

import br.com.felipefaustini.core.preferences.PreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PreferencesManager.getToken()
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .addHeader("Content-Type", "application/json")
        if (token.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}