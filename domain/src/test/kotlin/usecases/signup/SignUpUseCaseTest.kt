package usecases.signup

import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.usecases.signup.SignUpUseCase
import br.com.felipefaustini.domain.utils.Result
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import repository.FakeNewsRepository

@ExperimentalCoroutinesApi
class SignUpUseCaseTest {

    private lateinit var repository: FakeNewsRepository

    private lateinit var useCase: SignUpUseCase

    @Before
    fun beforeEachTest() {
        repository = FakeNewsRepository()

        useCase = SignUpUseCase(repository)
    }

    @Test
    fun signUp_returnSuccessToken() = runBlockingTest {
        val expected = Result.Success(Token("123"))
        val name = "Felipe"
        val email = "email"
        val password = "123"

        repository.updateTokenForTesting(Token("123"))

        val result = useCase.signUp(name, email, password)

        assertEquals(expected, result)
    }

    @Test
    fun signUp_returnError() = runBlockingTest {
        val error = Exception("Connection Error")
        val expected = Result.Error(error.message, error)
        val name = "Felipe"
        val email = "email"
        val password = "123"

        repository.updateTokenForTesting(null)
        repository.updateExceptionForTesting(error)

        val result = useCase.signUp(name, email, password)

        assertEquals(expected, result)
    }

}