package br.com.felipefaustini.domain.usecases.signup

import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.utils.Result

interface ISignUpUseCase {
    suspend fun signUp(name: String, email: String, password: String): Result<Token>
}