package br.com.felipefaustini.mesanews.presentation.signin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.usecases.signin.SignInUseCase
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.MainCoroutineRule
import br.com.felipefaustini.mesanews.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class SignInViewModelTest {

    /**
     * A rule is a way to run code before and after the execution of a test in JUnit. Two rules are
     * used to allow us to test SignInViewModel in an off-device test:
     *
     * InstantTaskExecutorRule is a JUnit rule that configures LiveData to execute each task synchronously
     *
     * MainCoroutineScopeRule is a custom rule in this codebase that configures Dispatchers.Main to
     * use a TestCoroutineDispatcher from kotlinx-coroutines-test. This allows tests to advance a
     * virtual-clock for testing, and allows code to use Dispatchers.Main in unit tests.
     */


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: NewsRepository

    private lateinit var signInUseCase: SignInUseCase

    private lateinit var signInViewModel: SignInViewModel

    @Before
    fun beforeEachTest() {
        signInUseCase = SignInUseCase(repository)
        signInViewModel = SignInViewModel(signInUseCase)
    }

    @Test
    fun signIn_showErrorWhenEmailOrPasswordIfNotFilled() {
        signInViewModel.signIn()

        val result = signInViewModel.signInErrorFieldsLiveData.getOrAwaitValue()

        assertEquals(Unit, result)
    }

    @Test
    fun signIn_goToHomeIfSuccefullyLoggedIn() = runBlockingTest {
        signInViewModel.email = email
        signInViewModel.password = password

        whenever(repository.signIn(SignIn(email, password)))
            .thenReturn(Result.Success(Token(token)))

        signInViewModel.signIn()

        val result = signInViewModel.signInGoHomeLiveData.getOrAwaitValue()

        assertEquals(Unit, result)
    }

    @Test
    fun signIn_showErrorIfSomeErrorOccurredWhenTryingToSignIn() = runBlockingTest {
        signInViewModel.email = email
        signInViewModel.password = password

        whenever(repository.signIn(SignIn(email, password)))
            .thenReturn(Result.Error(ErrorEntity.Network))

        signInViewModel.signIn()

        val result = signInViewModel.errorMessageLiveData.getOrAwaitValue()

        assertEquals(true, !result.isNullOrEmpty())
    }

    companion object {
        private const val email = "email"
        private const val password = "123"
        private const val token = "123"
    }

}