package br.com.felipefaustini.domain.usecases

import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.utils.Result

interface ISignInUseCase {
    suspend fun signIn(email: String, password: String): Result<Token>
}