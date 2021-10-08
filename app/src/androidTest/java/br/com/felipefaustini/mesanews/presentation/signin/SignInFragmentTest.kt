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
import br.com.felipefaustini.mesanews.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class SignInFragmentTest {

    private lateinit var scenario: FragmentScenario<SignInFragment>
    private lateinit var navController: NavController

    @Test
    fun clickSignIn_performSignInAction() {
        scenario = launchFragmentInContainer<SignInFragment>(
            null,
            R.style.Toolkit_Theme_MesaNews
        )
        navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        onView(withId(R.id.input_email))
            .perform(typeText("felipe.faustini@mesainc.com.br"))
        onView(withId(R.id.input_password))
            .perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.btn_sign_in)).perform(click())
        onView(withId(R.id.container_loading)).check(matches(isDisplayed()))

//        verify(navController).navigate(R.id.action_signInFragment_to_homeFragment)
    }

}