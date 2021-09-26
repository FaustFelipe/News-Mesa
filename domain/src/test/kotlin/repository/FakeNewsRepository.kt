package repository

import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.utils.Result

class FakeNewsRepository: NewsRepository {

    private var token: Token? = null
    fun updateTokenForTesting(token: Token?) {
        this.token = token
    }

    private var exception: Exception? = null
    fun updateExceptionForTesting(exception: Exception) {
        this.exception = exception
    }

    override suspend fun signIn(signIn: SignIn): Result<Token> {
        token?.let { return Result.Success(it) }
        return Result.Error(
            "Connection Error",
            exception ?: Exception("Connection Error")
        )
    }

}