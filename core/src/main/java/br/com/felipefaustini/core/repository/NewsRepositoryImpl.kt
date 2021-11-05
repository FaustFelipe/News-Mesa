package br.com.felipefaustini.core.repository

import br.com.felipefaustini.core.api.NewsApi
import br.com.felipefaustini.core.models.mappers.NewsMapper
import br.com.felipefaustini.core.models.mappers.SignInMapper
import br.com.felipefaustini.core.models.mappers.SignUpMapper
import br.com.felipefaustini.core.preferences.PreferencesManager
import br.com.felipefaustini.core.utils.handleResponseCall
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
        return@safeCall handleResponseCall(api.postSignIn(request)) { data ->
            Token(token = data.token)
        }
    }

    override suspend fun signUp(signUp: SignUp): Result<Token> = safeCall(coroutineContext) {
        val request = SignUpMapper.map(signUp)
        return@safeCall handleResponseCall(api.postSignUp(request)) { data ->
            Token(token = data.token)
        }
    }

    override suspend fun getHighlights(): Result<List<News>> = safeCall(coroutineContext) {
        return@safeCall handleResponseCall(api.getHighlights()) { data ->
            data.data?.map { NewsMapper.map(it) } ?: emptyList()
        }
    }

    override suspend fun getNews(): Result<List<News>> = safeCall(coroutineContext) {
        return@safeCall handleResponseCall(api.getNews()) { data ->
            data.data?.map { NewsMapper.map(it) } ?: emptyList()
        }
    }

    override fun saveToken(token: Token) {
        PreferencesManager.saveToken(token.token)
    }

    override fun getToken(): String {
        return PreferencesManager.getToken()
    }

    override fun signOut() {
        PreferencesManager.clearPreferences()
    }

}