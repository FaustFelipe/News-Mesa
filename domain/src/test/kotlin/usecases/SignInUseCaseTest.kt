package usecases

import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.usecases.SignInUseCase
import br.com.felipefaustini.domain.utils.Result
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import repository.FakeNewsRepository

@ExperimentalCoroutinesApi
class SignInUseCaseTest {

    private lateinit var repository: FakeNewsRepository

    private lateinit var useCase: SignInUseCase

    @Before
    fun beforeEachTest() {
        repository = FakeNewsRepository()

        useCase = SignInUseCase(repository)
    }

    @Test
    fun signIn_returnSuccessToken() = runBlockingTest {
        val expected = Result.Success(Token("123"))
        val email = "email"
        val password = "123"

        repository.updateTokenForTesting(Token("123"))

        val result = useCase.signIn(email, password)

        assertEquals(expected, result)
    }

    @Test
    fun signIn_returnError() = runBlockingTest {
        val error = Exception("Connection Error")
        val expected = Result.Error(error.message, error)
        val email = "email"
        val password = "123"

        repository.updateTokenForTesting(null)
        repository.updateExceptionForTesting(error)

        val result = useCase.signIn(email, password)

        assertEquals(expected, result)
    }

}