package br.com.felipefaustini.mesanews.presentation.home

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import br.com.felipefaustini.mesanews.R
import br.com.felipefaustini.mesanews.utils.IdleResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
@MediumTest
class HomeFragmentTest : KoinTest {

    private lateinit var scenario: FragmentScenario<HomeFragment>
    private lateinit var navController: NavController

    private lateinit var fakeHomeUseCase: FakeHomeUseCase

    lateinit var mockModule: Module

    @Before
    fun loadModules() {
        fakeHomeUseCase = FakeHomeUseCase()

        mockModule = module {
            single<IHomeUseCase>(override = true) { fakeHomeUseCase }
        }

        loadKoinModules(mockModule)
    }

    @After
    fun unloadModules() {
        unloadKoinModules(mockModule)
    }

    @Before
    fun setupScenario() {
        scenario = launchFragmentInContainer<HomeFragment>(
            null,
            R.style.Toolkit_Theme_MesaNews
        )
        navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
    }

    @Test
    fun clickFourthItem_shouldOpenDetailsNews() {
        IdlingRegistry.getInstance().register(IdleResource.instanceHighlights)

        // TODO implement
    }

    @Test
    fun clickThirdItem_shouldOpenDetailsNews() {
        IdlingRegistry.getInstance().register(IdleResource.instanceNews)

        // TODO implement
    }

    companion object {
        private const val TIMEOUT = 2_000L
    }

}