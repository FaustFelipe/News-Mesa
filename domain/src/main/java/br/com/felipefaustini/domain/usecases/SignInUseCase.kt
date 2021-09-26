package br.com.felipefaustini.domain.usecases

import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.utils.Result

class SignInUseCase(
    private val repository: NewsRepository
) : ISignInUseCase {

    override suspend fun signIn(email: String, password: String): Result<Token> {
        val signIn = SignIn(email, password)
        return repository.signIn(signIn)
    }

}