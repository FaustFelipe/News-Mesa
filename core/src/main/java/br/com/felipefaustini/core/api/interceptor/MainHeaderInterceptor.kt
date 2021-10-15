package br.com.felipefaustini.core.api.interceptor

import br.com.felipefaustini.core.preferences.PreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class MainHeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PreferencesManager.getToken()
        val response = chain
            .proceed(
                chain
                    .request()
                    .newBuilder()
                    .apply {
                        addHeader("Content-Type", "application/json")
                        if (token.isNotEmpty()) {
                            addHeader("Authorization", "Bearer $token")
                        }
                    }.build()
            )
        if (response.code == 401) {
            // TODO Invalid token, try to relogin or redirect to sign in
        }
        return response
    }

}