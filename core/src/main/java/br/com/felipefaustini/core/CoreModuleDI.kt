package br.com.felipefaustini.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    single { provideHttpLoggingInterceptor() }

    fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val client =  OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            client.addInterceptor(interceptor)
        }
        return client.build()
    }

    single { provideOkhttpClient(interceptor = get()) }

    fun provideRetrofit(client: OkHttpClient): NewsApi {
        val baseUrl = ""

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    single { provideRetrofit(get()) }

}