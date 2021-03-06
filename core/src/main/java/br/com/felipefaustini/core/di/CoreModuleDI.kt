package br.com.felipefaustini.core.di

import br.com.felipefaustini.core.BuildConfig
import br.com.felipefaustini.core.api.NewsApi
import br.com.felipefaustini.core.api.interceptor.AuthTokenInterceptor
import br.com.felipefaustini.core.api.interceptor.CacheInterceptor
import br.com.felipefaustini.core.api.interceptor.ErrorInterceptor
import br.com.felipefaustini.core.api.interceptor.ForceCacheInterceptor
import br.com.felipefaustini.core.repository.NewsRepositoryImpl
import br.com.felipefaustini.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return interceptor
    }

    factory { provideHttpLoggingInterceptor() }

    factory { ErrorInterceptor() }

    factory { CacheInterceptor() }

    factory { ForceCacheInterceptor() }

    factory { AuthTokenInterceptor() }

    fun provideOkhttpClient(
        interceptor: HttpLoggingInterceptor,
        errorInterceptor: ErrorInterceptor,
        cacheInterceptor: CacheInterceptor,
        forceCacheInterceptor: ForceCacheInterceptor,
        authTokenInterceptor: AuthTokenInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(errorInterceptor)
//            .addNetworkInterceptor(cacheInterceptor)
//            .addInterceptor(forceCacheInterceptor)
            .addInterceptor(authTokenInterceptor)
        return client.build()
    }

    single {
        provideOkhttpClient(
            interceptor = get(),
            errorInterceptor = get(),
            cacheInterceptor = get(),
            forceCacheInterceptor = get(),
            authTokenInterceptor = get()
        )
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val baseUrl = BuildConfig.BASE_URL

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single { provideRetrofit(get()) }

    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    single { provideNewsApi(get()) }

}

val repositoryModule = module {

    fun provideRepositoryModule(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi, Dispatchers.IO)
    }

    single { provideRepositoryModule(get()) }

}