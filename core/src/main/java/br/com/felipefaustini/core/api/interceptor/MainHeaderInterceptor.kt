package br.com.felipefaustini.core.api.interceptor

import br.com.felipefaustini.core.preferences.PreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class MainHeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PreferencesManager.getToken()
        return chain
            .proceed(
                chain
                    .request()
                    .newBuilder()
                    .apply {
                        addHeader("Content-Type", "application/json")
                        if (token.isNotEmpty()) {
                            addHeader("Access-Token", token)
                        }
                    }.build()
            )
    }

}