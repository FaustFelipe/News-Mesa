package usecases.signin

import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.usecases.signin.SignInUseCase
import br.com.felipefaustini.domain.utils.Result
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class SignInUseCaseTest {

    @Mock
    private lateinit var repository: NewsRepository

    private lateinit var useCase: SignInUseCase

    @Before
    fun beforeEachTest() {
        useCase = SignInUseCase(repository)
    }

    @Test
    fun signIn_returnSuccessToken() = runBlockingTest {
        val expected = Result.Success(Token("123"))
        val email = "email"
        val password = "123"
        val signIn = SignIn(email, password)

        whenever(repository.signIn(signIn))
            .thenReturn(Result.Success(Token(token = "123")))

        val result = useCase.signIn(email, password)

        verify(repository).saveToken(Token(token = "123"))
        verify(repository).signIn(signIn)
        verifyNoMoreInteractions(repository)
        assertEquals(expected, result)
    }

    @Test
    fun signIn_returnError() = runBlockingTest {
        val error = Exception("Connection Error")
        val expected = Result.Error(error.message, error)
        val email = "email"
        val password = "123"
        val signIn = SignIn(email, password)

        whenever(repository.signIn(signIn))
            .thenReturn(Result.Error(error.message, error))

        val result = useCase.signIn(email, password)

        verify(repository).signIn(signIn)
        verifyNoMoreInteractions(repository)
        assertEquals(expected, result)
    }

}