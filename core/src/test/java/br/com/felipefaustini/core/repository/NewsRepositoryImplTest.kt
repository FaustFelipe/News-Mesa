package br.com.felipefaustini.core.repository

import android.content.SharedPreferences
import br.com.felipefaustini.core.api.NewsApi
import br.com.felipefaustini.core.models.request.SignInRequest
import br.com.felipefaustini.core.models.request.SignUpRequest
import br.com.felipefaustini.core.models.response.TokenResponse
import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.SignUp
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

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class NewsRepositoryImplTest {

    @Mock
    private lateinit var newsApi: NewsApi

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var repository: NewsRepositoryImpl

    @Before
    fun beforeEachTest() {
        repository = NewsRepositoryImpl(
            newsApi,
            TestCoroutineDispatcher(),
            sharedPreferences
        )
    }

    @Test
    fun signIn_returnValidToken() = runBlockingTest {
        val email = "felipe.faustini@email.com"
        val password = "123"
        val token = "123"

        whenever(
            newsApi.postSignIn(SignInRequest(email = email, password = password))
        ).thenReturn(Response.success(TokenResponse(token = token)))

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

    @Test
    fun signUp_returnValidToken() = runBlockingTest {
        val name = "Felipe"
        val email = "felipe@email.com"
        val password = "123"
        val token = "123"

        val signUpRequest = SignUpRequest(name, email, password)

        whenever(newsApi.postSignUp(signUpRequest))
            .thenReturn(Response.success(TokenResponse(token)))

        val response = repository.signUp(SignUp(name, email, password))

        verify(newsApi).postSignUp(signUpRequest)
        assertEquals(Result.Success(Token(token)), response)
    }

    @Test
    fun signUp_returnError() = runBlockingTest {
        val expectedException = Exception("Connection error")
        val name = "Felipe"
        val email = "felipe@email.com"
        val password = "123"

        val signUpRequest = SignUpRequest(name, email, password)

        whenever(newsApi.postSignUp(signUpRequest))
            .thenAnswer { throw expectedException }

        val response = repository.signUp(SignUp(name, email, password))

        verify(newsApi).postSignUp(SignUpRequest(name, email, password))
        assertEquals(Result.Error(expectedException.message, expectedException), response)
    }

}