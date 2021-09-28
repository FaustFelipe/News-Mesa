package br.com.felipefaustini.mesanews.presentation.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.felipefaustini.mesanews.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeSignUpUseCase: FakeSignUpUseCase

    private lateinit var signUpViewModel: SignUpViewModel

    @Before
    fun beforeEachTest() {
        fakeSignUpUseCase = FakeSignUpUseCase()

        signUpViewModel = SignUpViewModel(fakeSignUpUseCase)
    }

    @Test
    fun signUp_returnFieldsEmptyError() {
        signUpViewModel.signUp()

        val result = signUpViewModel.errorFieldsEmpty.getOrAwaitValue()

        assertEquals(Unit, result)
    }

    @Test
    fun signUp_goToHomeIfSuccefullySignUp() {
        signUpViewModel.name = "Felipe"
        signUpViewModel.email = "email"
        signUpViewModel.password = "123"

        signUpViewModel.signUp()

        val result = signUpViewModel.signUpGoHomeLiveData.getOrAwaitValue()

        assertEquals(Unit, result)
    }

    @Test
    fun signUp_showErrorIfSomeErrorOccurredWhenTryingToSignUp() {
        signUpViewModel.name = "Felipe"
        signUpViewModel.email = "email"
        signUpViewModel.password = "123"

        fakeSignUpUseCase.updateShowErrorForTesting(true)

        signUpViewModel.signUp()

        val result = signUpViewModel.errorMessageLiveData.getOrAwaitValue()

        assertEquals(true, !result.isNullOrEmpty())
    }

}