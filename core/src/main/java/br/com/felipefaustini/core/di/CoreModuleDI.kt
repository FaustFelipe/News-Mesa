package br.com.felipefaustini.core.di

import android.content.Context
import android.content.SharedPreferences
import br.com.felipefaustini.core.BuildConfig
import br.com.felipefaustini.core.api.NewsApi
import br.com.felipefaustini.core.repository.NewsRepositoryImpl
import br.com.felipefaustini.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
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

    single { provideHttpLoggingInterceptor() }

    fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val client =  OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
        return client.build()
    }

    single { provideOkhttpClient(interceptor = get()) }

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

    fun providePrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("mesa_news_prefs", Context.MODE_PRIVATE)
    }

    fun provideRepositoryModule(newsApi: NewsApi, preferences: SharedPreferences): NewsRepository {
        return NewsRepositoryImpl(newsApi, Dispatchers.IO, preferences)
    }

    single { provideRepositoryModule(get(), get()) }
    single { providePrefs(androidContext()) }

}