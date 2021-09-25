package br.com.felipefaustini.mesanews

import android.app.Application
import br.com.felipefaustini.core.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class NewsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@NewsApplication)
            koin.loadModules(
                listOf(
                    networkModule
                )
            )
        }

        /*
        properties(
                mapOf(
                    Property.API_BASE_URL to BuildConfig.API_BASE_URL,
                    Property.AWS_BASE_URL to BuildConfig.AWS_API_BASE_URL,
                    StoreModule.PROPERTY_BASE_URL to "${BuildConfig.API_BASE_URL}v2/trainer/vtex/",
                )
            )
        * */
    }

    override fun onTerminate() {
        super.onTerminate()

        stopKoin()
    }

}