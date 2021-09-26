package br.com.felipefaustini.core.repository

import br.com.felipefaustini.core.models.mappers.SignInMapper
import br.com.felipefaustini.core.api.NewsApi
import br.com.felipefaustini.core.utils.safeCall
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.Token
import kotlin.coroutines.CoroutineContext

class NewsRepositoryImpl(
    private val api: NewsApi,
    private val coroutineContext: CoroutineContext
): NewsRepository {

    override suspend fun signIn(signIn: SignIn): Result<Token> = safeCall(coroutineContext) {
        val request = SignInMapper.map(signIn)
        val response = api.postSignIn(request)
        val headers = response.headers()
        val data = response.body()!!
        Result.Success(Token(token = data.token))
    }

}