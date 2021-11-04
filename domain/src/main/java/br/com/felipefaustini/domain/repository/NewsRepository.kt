package br.com.felipefaustini.domain.repository

import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.SignUp
import br.com.felipefaustini.domain.models.Token

interface NewsRepository {
    suspend fun signIn(signIn: SignIn): Result<Token>
    suspend fun signUp(signUp: SignUp): Result<Token>
    suspend fun getHighlights(): Result<List<News>>
    suspend fun getNews(): Result<List<News>>
    fun saveToken(token: Token)
    fun getToken(): String
}