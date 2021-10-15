package br.com.felipefaustini.mesanews.presentation.splash

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.felipefaustini.core.preferences.PreferencesManager
import br.com.felipefaustini.domain.usecases.splash.ISplashUseCase
import br.com.felipefaustini.mesanews.R
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
@MediumTest
class SplashFragmentTest: KoinTest {

    private lateinit var scenario: FragmentScenario<SplashFragment>
    private lateinit var navController: NavController

    private lateinit var fakeUseCase: FakeSplashUseCase

    lateinit var mockModule: Module

    @Before
    fun loadModules() {
        fakeUseCase = FakeSplashUseCase()

        mockModule = module {
            single<ISplashUseCase>(override = true) { fakeUseCase }
        }

        loadKoinModules(mockModule)
    }

    @Before
    fun setupScenario() {
        scenario = launchFragmentInContainer(
            null,
            R.style.Toolkit_Theme_MesaNews
        )
        navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
    }

    @After
    fun unloadModules() {
        unloadKoinModules(mockModule)
    }

    @Test
    fun onOpen_shouldOpenOnBoardingIfUserIsNotSignedIn() {
        fakeUseCase.isSignedIn = false

        verify(navController, timeout(TIMEOUT))
            .navigate(R.id.action_splashFragment_to_onboardingFragment, null)
    }

    @Test
    fun onOpen_shouldOpenHomeIfUserIsSignedIn() {
        fakeUseCase.isSignedIn = true

        verify(navController, timeout(TIMEOUT))
            .navigate(R.id.action_splashFragment_to_homeFragment, null)
    }

    companion object {
        private const val TIMEOUT = 2_000L
    }

}