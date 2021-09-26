package br.com.felipefaustini.mesanews.presentation.signin

import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.usecases.ISignInUseCase
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.delay

class FakeSignInUseCase: ISignInUseCase {

    private var showError = false
    fun updateShowErrorForTesting(error: Boolean) {
        showError = error
    }

    override suspend fun signIn(email: String, password: String): Result<Token> {
        delay(1500L)
        val exception = Exception("Connection Error")
        return if (!showError) Result.Success(Token("123"))
        else Result.Error(exception.message, exception)
    }

}