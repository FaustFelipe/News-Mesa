package br.com.felipefaustini.mesanews.presentation.signup

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.felipefaustini.domain.usecases.signup.ISignUpUseCase
import br.com.felipefaustini.mesanews.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
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
@ExperimentalCoroutinesApi
class SignUpFragmentTest: KoinTest {

    private lateinit var scenario: FragmentScenario<SignUpFragment>
    private lateinit var navController: NavController

    private lateinit var fakeUseCase: FakeSignUpUseCase

    lateinit var mockModule: Module

    @Before
    fun loadModules() {
        fakeUseCase = FakeSignUpUseCase()

        mockModule = module {
            single<ISignUpUseCase>(override = true) { fakeUseCase }
        }

        loadKoinModules(mockModule)
    }

    @Before
    fun setupScenario() {
        scenario = launchFragmentInContainer<SignUpFragment>(
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
    fun clickSignUp_performSignUpAction() = runBlockingTest {
        onView(withId(R.id.input_name)).perform(typeText(NAME))
        onView(withId(R.id.input_email)).perform(typeText(EMAIL))
        onView(withId(R.id.input_password)).perform(typeText(PASSWORD), closeSoftKeyboard())
        onView(withId(R.id.btn_sign_up)).perform(click())
        onView(withId(R.id.container_loading)).check(matches(isDisplayed()))

        verify(navController, timeout(TIMEOUT))
            .navigate(R.id.action_signUpFragment_to_homeFragment, null)
    }

    companion object {
        private const val TIMEOUT = 2_000L
        private const val NAME = "Felipe"
        private const val EMAIL = "felipefaustini@email.com"
        private const val PASSWORD = "123456"
    }

}