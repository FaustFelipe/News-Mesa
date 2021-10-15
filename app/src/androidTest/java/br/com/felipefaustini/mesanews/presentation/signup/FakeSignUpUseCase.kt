package br.com.felipefaustini.mesanews.presentation.signup

import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.usecases.signup.ISignUpUseCase
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.delay

class FakeSignUpUseCase: ISignUpUseCase {
    override suspend fun signUp(name: String, email: String, password: String): Result<Token> {
        delay(1_000L)
        return Result.Success(Token("123"))
    }
}
