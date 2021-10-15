package br.com.felipefaustini.mesanews.presentation.signin

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
import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.usecases.signin.ISignInUseCase
import br.com.felipefaustini.domain.utils.Result
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
import org.mockito.kotlin.whenever

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class SignInFragmentTest: KoinTest {

    private lateinit var scenario: FragmentScenario<SignInFragment>
    private lateinit var navController: NavController

    private lateinit var fakeSignInUseCase: FakeSignInUseCase

    lateinit var mockModule: Module

    @Before
    fun loadModules() {
        fakeSignInUseCase = FakeSignInUseCase()

        mockModule = module {
            single<ISignInUseCase>(override = true) { fakeSignInUseCase }
        }

        loadKoinModules(mockModule)
    }

    @After
    fun unloadModules() {
        unloadKoinModules(mockModule)
    }

    @Before
    fun setupScenario() {
        scenario = launchFragmentInContainer<SignInFragment>(
            null,
            R.style.Toolkit_Theme_MesaNews
        )
        navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
    }

    @Test
    fun clickSignIn_performSignInAction() = runBlockingTest {
        onView(withId(R.id.input_email)).perform(typeText(EMAIL))
        onView(withId(R.id.input_password)).perform(typeText(PASSWORD), closeSoftKeyboard())
        onView(withId(R.id.btn_sign_in)).perform(click())
        onView(withId(R.id.container_loading)).check(matches(isDisplayed()))

        verify(navController, timeout(TIMEOUT))
            .navigate(R.id.action_signInFragment_to_homeFragment, null)
    }

    @Test
    fun clickSignUp_goToSignUpScreen() {
        onView(withId(R.id.btn_create_account)).perform(click())

        verify(navController)
            .navigate(R.id.action_signInFragment_to_signUpFragment, null)
    }

    companion object {
        private const val TIMEOUT = 2_000L
        private const val EMAIL = "felipefaustini@email.com"
        private const val PASSWORD = "123456"
    }

}