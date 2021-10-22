package br.com.felipefaustini.mesanews.presentation.signup

import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.usecases.signup.ISignUpUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.utils.IdleResource
import kotlinx.coroutines.delay

class FakeSignUpUseCase: ISignUpUseCase {

    override suspend fun signUp(name: String, email: String, password: String): Result<Token> {
        IdleResource.instanceSignUp.increment()
        delay(1_500L)
        IdleResource.instanceSignUp.decrement()
        return Result.Success(Token("123"))
    }

}
