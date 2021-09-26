package br.com.felipefaustini.core.repository

import br.com.felipefaustini.core.api.NewsApi
import br.com.felipefaustini.core.models.request.SignInRequest
import br.com.felipefaustini.core.models.response.SignInResponse
import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.utils.Result
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest {

    @Mock
    private lateinit var newsApi: NewsApi

    private lateinit var repository: NewsRepositoryImpl

    @Before
    fun beforeEachTest() {
        repository = NewsRepositoryImpl(newsApi, TestCoroutineDispatcher())
    }

    @Test
    fun signIn_returnValidToken() = runBlockingTest {
        val email = "felipe.faustini@email.com"
        val password = "123"
        val token = "123"

        whenever(
            newsApi.postSignIn(SignInRequest(email = email, password = password))
        ).thenReturn(Response.success(SignInResponse(token = token)))

        val response = repository.signIn(SignIn(email = email, password = password))

        verify(newsApi).postSignIn(SignInRequest(email = email, password = password))
        assertEquals(Result.Success(Token(token = token)), response)
    }

    @Test
    fun signIn_returnError() = runBlockingTest {
        val expectedException = Exception("Connection error")
        val email = "felipe.faustini@email.com"
        val password = "123"

        whenever(
            newsApi.postSignIn(SignInRequest(email = email, password = password))
        ).thenAnswer { throw expectedException }

        val response = repository.signIn(SignIn(email = email, password = password))

        verify(newsApi).postSignIn(SignInRequest(email = email, password = password))
        assertEquals(Result.Error(expectedException.message, expectedException), response)
    }

}