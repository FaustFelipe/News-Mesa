package usecases.signup

import br.com.felipefaustini.domain.models.SignUp
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.usecases.signup.SignUpUseCase
import br.com.felipefaustini.domain.utils.ErrorEntity
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
class SignUpUseCaseTest {

    @Mock
    private lateinit var repository: NewsRepository

    private lateinit var useCase: SignUpUseCase

    @Before
    fun beforeEachTest() {
        useCase = SignUpUseCase(repository)
    }

    @Test
    fun signUp_returnSuccessToken() = runBlockingTest {
        val expected = Result.Success(Token("123"))

        val signUp = SignUp(name, email, password)

        whenever(repository.signUp(signUp))
            .thenReturn(Result.Success(Token("123")))

        val result = useCase.signUp(name, email, password)

        verify(repository).signUp(signUp)
        verify(repository).saveToken(Token("123"))
        verifyNoMoreInteractions(repository)
        assertEquals(expected, result)
    }

    @Test
    fun signUp_returnNetworkError() = runBlockingTest {
        val expected = Result.Error(ErrorEntity.Network)

        val signUp = SignUp(name, email, password)

        whenever(repository.signUp(signUp))
            .thenReturn(Result.Error(ErrorEntity.Network))

        val result = useCase.signUp(name, email, password)

        verify(repository).signUp(signUp)
        verifyNoMoreInteractions(repository)
        assertEquals(expected, result)
    }

    @Test
    fun signUp_returnNotFoundError() = runBlockingTest {
        val expected = Result.Error(ErrorEntity.NotFound)

        val signUp = SignUp(name, email, password)

        whenever(repository.signUp(signUp))
            .thenReturn(Result.Error(ErrorEntity.NotFound))

        val result = useCase.signUp(name, email, password)

        verify(repository).signUp(signUp)
        verifyNoMoreInteractions(repository)
        assertEquals(expected, result)
    }

    companion object {
        private val name = "Felipe"
        private val email = "email"
        private val password = "123"
    }

}