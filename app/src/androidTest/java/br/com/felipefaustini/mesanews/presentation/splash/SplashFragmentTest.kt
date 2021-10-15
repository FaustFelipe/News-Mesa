package br.com.felipefaustini.mesanews.presentation.splash

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.felipefaustini.core.preferences.PreferencesManager
import br.com.felipefaustini.mesanews.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@MediumTest
class SplashFragmentTest {

    private lateinit var scenario: FragmentScenario<SplashFragment>
    private lateinit var navController: NavController

    @Before
    fun beforeEachTest() {
        scenario = launchFragmentInContainer(
            null,
            R.style.Toolkit_Theme_MesaNews
        )
        navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
    }

    @Test
    fun onOpenShouldGoToOnboarding() {

    }

}