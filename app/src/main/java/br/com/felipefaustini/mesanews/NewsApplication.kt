package br.com.felipefaustini.mesanews

import android.app.Application
import br.com.felipefaustini.core.di.networkModule
import br.com.felipefaustini.core.di.repositoryModule
import br.com.felipefaustini.core.preferences.PreferencesManager.setupPreferences
import br.com.felipefaustini.domain.di.useCasesModule
import br.com.felipefaustini.mesanews.di.viewModulesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class NewsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupPreferences()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@NewsApplication)
            koin.loadModules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCasesModule,
                    viewModulesModule
                )
            )
        }
    }

    override fun onTerminate() {
        super.onTerminate()

        stopKoin()
    }

}