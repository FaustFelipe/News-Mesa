package br.com.felipefaustini.domain.usecases.signin

import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.utils.Result

class SignInUseCase(
    private val repository: NewsRepository
): ISignInUseCase {

    override suspend fun signIn(email: String, password: String): Result<Token> {
        val signIn = SignIn(email, password)
        val response = repository.signIn(signIn)
        if (response is Result.Success) {
            repository.saveToken(response.data)
        }
        return response
    }

}