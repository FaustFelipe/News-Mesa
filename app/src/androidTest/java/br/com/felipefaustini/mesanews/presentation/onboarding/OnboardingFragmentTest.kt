package br.com.felipefaustini.mesanews.presentation.onboarding

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.felipefaustini.mesanews.R
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@MediumTest
class OnboardingFragmentTest {

    private lateinit var scenario: FragmentScenario<OnboardingFragment>
    private lateinit var navController: NavController

    @Test
    fun clickSignIn_navigateToSignInFragment() {
        // GIVEN - On the onboarding screen
        scenario = launchFragmentInContainer<OnboardingFragment>(
            null,
            R.style.Toolkit_Theme_MesaNews
        )
        navController = mock(NavController::class.java)

        val resId = R.id.action_splashFragment_to_signInFragment
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // WHEN - Click on sign in button
        onView(withId(R.id.btn_sign_in)).perform(click())

        // THEN - Verify that we navigate to the signin screen
        verify(navController)
            .navigate(
                resId,
                null
            )
    }

    @Test
    fun clickSignUp_navigateToSignUpFragment() {
        // GIVEN - On the onboarding screen
        scenario = launchFragmentInContainer<OnboardingFragment>(
            null,
            R.style.Toolkit_Theme_MesaNews
        )
        navController = mock(NavController::class.java)

        val resId = R.id.action_splashFragment_to_signUpFragment
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // WHEN - Click on sign up button
        onView(withId(R.id.btn_create_account)).perform(click())

        // THEN - Verify that we navigate to the signup screen
        verify(navController)
            .navigate(
                resId,
                null
            )
    }

}