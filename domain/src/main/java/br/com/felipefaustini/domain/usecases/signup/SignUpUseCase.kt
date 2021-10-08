package br.com.felipefaustini.domain.usecases.signup

import br.com.felipefaustini.domain.models.SignUp
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.utils.Result

class SignUpUseCase(
    private val repository: NewsRepository
) {

    suspend fun signUp(name: String, email: String, password: String): Result<Token> {
        val signUp = SignUp(name, email, password)
        val response = repository.signUp(signUp)
        if (response is Result.Success) {
            repository.saveToken(response.data)
        }
        return response
    }

}