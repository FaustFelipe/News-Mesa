package br.com.felipefaustini.mesanews.presentation.signup

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.felipefaustini.mesanews.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
@MediumTest
class SignUpFragmentTest {

    private lateinit var scenario: FragmentScenario<SignUpFragment>
    private lateinit var navController: NavController

    @Before
    fun beforeEachTest() {
        scenario = launchFragmentInContainer(
            null,
            R.style.Toolkit_Theme_MesaNews
        )
        navController = mock(NavController::class.java)
    }

    @Test
    fun clickSignUp_performSignUpAction() {
        onView(withId(R.id.input_name)).perform(typeText("Felipe"))
        onView(withId(R.id.input_email)).perform(typeText("felipefaustini@email.com"))
        onView(withId(R.id.input_password))
            .perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.btn_sign_up)).perform(click())
        onView(withId(R.id.container_loading)).check(matches(isDisplayed()))
    }

}