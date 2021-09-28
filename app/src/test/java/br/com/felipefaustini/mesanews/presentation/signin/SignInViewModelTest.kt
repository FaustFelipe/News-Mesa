package br.com.felipefaustini.mesanews.presentation.signin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.felipefaustini.mesanews.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignInViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeSignInUseCase: FakeSignInUseCase

    private lateinit var signInViewModule: SignInViewModel

    @Before
    fun beforeEachTest() {
        fakeSignInUseCase = FakeSignInUseCase()
        signInViewModule = SignInViewModel(fakeSignInUseCase)
    }

    @Test
    fun signIn_showErrorWhenEmailOrPasswordIfNotFilled() {
        signInViewModule.signIn()

        val result = signInViewModule.signInErrorFieldsLiveData.getOrAwaitValue()

        assertEquals(Unit, result)
    }

    @Test
    fun signIn_goToHomeIfSuccefullyLoggedIn() {
        signInViewModule.email = "email"
        signInViewModule.password = "123"

        signInViewModule.signIn()

        val result = signInViewModule.signInGoHomeLiveData.getOrAwaitValue()

        assertEquals(Unit, result)
    }

    @Test
    fun signIn_showErrorIfSomeErrorOccurredWhenTryingToSignIn() {
        signInViewModule.email = "email"
        signInViewModule.password = "123"

        fakeSignInUseCase.updateShowErrorForTesting(true)

        signInViewModule.signIn()

        val result = signInViewModule.errorMessageLiveData.getOrAwaitValue()

        assertEquals(true, !result.isNullOrEmpty())
    }

}