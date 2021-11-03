package br.com.felipefaustini.core.api.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class ForceCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        // Why do should we use it? Even when the data is cached and the Internet is not available,
        // it returns with the error "no internet available".
//        if (!IsInternetAvailable()) {
//            builder.cacheControl(CacheControl.FORCE_CACHE)
//        }
        return chain.proceed(builder.build())
    }
}