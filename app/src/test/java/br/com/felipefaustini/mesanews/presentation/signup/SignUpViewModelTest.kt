package br.com.felipefaustini.mesanews.presentation.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.usecases.signup.SignUpUseCase
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.MainCoroutineRule
import br.com.felipefaustini.mesanews.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class SignUpViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var signUpUseCase: SignUpUseCase

    @InjectMocks
    private lateinit var signUpViewModel: SignUpViewModel

    @Test
    fun signUp_returnFieldsEmptyError() {
        signUpViewModel.signUp()

        val result = signUpViewModel.errorFieldsEmpty.getOrAwaitValue()

        assertEquals(Unit, result)
    }

    @Test
    fun signUp_goToHomeIfSuccefullySignUp() = runBlockingTest {
        signUpViewModel.name = name
        signUpViewModel.email = email
        signUpViewModel.password = password

        whenever(signUpUseCase.signUp(name, email, password))
            .thenReturn(Result.Success(Token(token)))

        signUpViewModel.signUp()

        val result = signUpViewModel.signUpGoHomeLiveData.getOrAwaitValue()

        assertEquals(Unit, result)
    }

    @Test
    fun signUp_showErrorIfSomeErrorOccurredWhenTryingToSignUp() = runBlockingTest {
        signUpViewModel.name = name
        signUpViewModel.email = email
        signUpViewModel.password = password

        whenever(signUpUseCase.signUp(name, email, password))
            .thenReturn(Result.Error(ErrorEntity.Network))

        signUpViewModel.signUp()

        val result = signUpViewModel.errorMessageLiveData.getOrAwaitValue()

        assertEquals(true, !result.isNullOrEmpty())
    }

    companion object {
        private const val name = "Felipe"
        private const val email = "email"
        private const val password = "123"
        private const val token = "123"
    }

}