package br.com.felipefaustini.mesanews.presentation.signin

import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.usecases.signin.ISignInUseCase
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.delay

class FakeSignInUseCase: ISignInUseCase {
    override suspend fun signIn(email: String, password: String): Result<Token> {
        delay(1_000L)
        return Result.Success(Token("123"))
    }
}