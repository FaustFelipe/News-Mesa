package br.com.felipefaustini.domain.repository

import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.Token

interface NewsRepository {
    suspend fun signIn(signIn: SignIn): Result<Token>
}