package br.com.felipefaustini.core.repository

import br.com.felipefaustini.core.api.NewsApi
import br.com.felipefaustini.core.models.mappers.NewsMapper
import br.com.felipefaustini.core.models.mappers.SignInMapper
import br.com.felipefaustini.core.models.mappers.SignUpMapper
import br.com.felipefaustini.core.preferences.PreferencesManager
import br.com.felipefaustini.core.utils.handleApiCodeException
import br.com.felipefaustini.core.utils.safeCall
import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.SignUp
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.utils.Result
import kotlin.coroutines.CoroutineContext

class NewsRepositoryImpl(
    private val api: NewsApi,
    private val coroutineContext: CoroutineContext
): NewsRepository {

    override suspend fun signIn(signIn: SignIn): Result<Token> = safeCall(coroutineContext) {
        val request = SignInMapper.map(signIn)
        val response = api.postSignIn(request)
        val headers = response.headers()
        if (!response.isSuccessful) {
            return@safeCall handleApiCodeException(response.code())
        }
        val data = response.body()!!
        Result.Success(Token(token = data.token))
    }

    override suspend fun signUp(signUp: SignUp): Result<Token> = safeCall(coroutineContext) {
        val request = SignUpMapper.map(signUp)
        val response = api.postSignUp(request)
        val headers = response.headers()
        if (!response.isSuccessful) {
            return@safeCall handleApiCodeException(response.code())
        }
        val data = response.body()!!
        Result.Success(Token(token = data.token))
    }

    override suspend fun getNews(): Result<List<News>> = safeCall(coroutineContext) {
        val response = api.getNews()
        val headers = response.headers()
        if (!response.isSuccessful) {
            return@safeCall handleApiCodeException(response.code())
        }
        val data = response.body()!!
        Result.Success(data.data?.map { NewsMapper.map(it) } ?: emptyList())
    }

    override fun saveToken(token: Token) {
        PreferencesManager.saveToken(token.token)
    }

    override fun getToken(): String {
        return PreferencesManager.getToken()
    }

    override fun isUserSidnedIn(): Boolean {
        return PreferencesManager.getToken().isNotEmpty()
    }

}