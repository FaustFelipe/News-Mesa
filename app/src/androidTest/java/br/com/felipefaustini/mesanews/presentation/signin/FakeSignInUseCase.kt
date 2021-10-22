package br.com.felipefaustini.mesanews.presentation.signin

import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.usecases.signin.ISignInUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.utils.IdleResource
import kotlinx.coroutines.delay

class FakeSignInUseCase: ISignInUseCase {

    override suspend fun signIn(email: String, password: String): Result<Token> {
        IdleResource.instanceSignIn.increment()
        delay(1_500L)
        IdleResource.instanceSignIn.decrement()
        return Result.Success(Token("123"))
    }

}