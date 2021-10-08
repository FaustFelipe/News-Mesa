package br.com.felipefaustini.core.repository

import android.content.SharedPreferences
import br.com.felipefaustini.core.api.NewsApi
import br.com.felipefaustini.core.models.request.SignInRequest
import br.com.felipefaustini.core.models.request.SignUpRequest
import br.com.felipefaustini.core.models.response.TokenResponse
import br.com.felipefaustini.domain.models.SignIn
import br.com.felipefaustini.domain.models.SignUp
import br.com.felipefaustini.domain.models.Token
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
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
        whenever(
            newsApi.postSignIn(SignInRequest(email = email, password = password))
        ).thenReturn(Response.success(TokenResponse(token = token)))

        val response = repository.signIn(SignIn(email = email, password = password))

        verify(newsApi).postSignIn(SignInRequest(email = email, password = password))
        assertEquals(Result.Success(Token(token = token)), response)
    }

    @Test
    fun signIn_returnNotFoundError() = runBlockingTest {
        whenever(
            newsApi.postSignIn(SignInRequest(email = email, password = password))
        ).thenReturn(
            Response.error(
                404,
                errorResponseBody.toResponseBody("application/json".toMediaTypeOrNull())
            )
        )

        val response = repository.signIn(SignIn(email = email, password = password))

        verify(newsApi).postSignIn(SignInRequest(email = email, password = password))
        assertEquals(Result.Error(ErrorEntity.NotFound), response)
    }

    @Test
    fun signIn_returnAccessDeniedError() = runBlockingTest {
        whenever(
            newsApi.postSignIn(SignInRequest(email, password))
        ).thenReturn(
            Response.error(
                403,
                errorResponseBody.toResponseBody("application/json".toMediaTypeOrNull())
            )
        )

        val response = repository.signIn(SignIn(email, password))

        verify(newsApi).postSignIn(SignInRequest(email, password))
        assertEquals(Result.Error(ErrorEntity.AccessDenied), response)
    }

    @Test
    fun signIn_returnUnauthorizedError() = runBlockingTest {
        whenever(
            newsApi.postSignIn(SignInRequest(email, password))
        ).thenReturn(
            Response.error(
                401,
                errorResponseBody.toResponseBody("application/json".toMediaTypeOrNull())
            )
        )

        val response = repository.signIn(SignIn(email, password))

        verify(newsApi).postSignIn(SignInRequest(email, password))
        assertEquals(Result.Error(ErrorEntity.Unauthorized), response)
    }

    @Test
    fun signIn_returnServiceUnavailableError() = runBlockingTest {
        whenever(
            newsApi.postSignIn(SignInRequest(email, password))
        ).thenReturn(
            Response.error(
                503,
                errorResponseBody.toResponseBody("application/json".toMediaTypeOrNull())
            )
        )

        val response = repository.signIn(SignIn(email, password))

        verify(newsApi).postSignIn(SignInRequest(email, password))
        assertEquals(Result.Error(ErrorEntity.ServiceUnavailable), response)
    }

    @Test
    fun signIn_returnUnknownError() = runBlockingTest {
        whenever(
            newsApi.postSignIn(SignInRequest(email, password))
        ).thenReturn(
            Response.error(
                400,
                errorResponseBody.toResponseBody("application/json".toMediaTypeOrNull())
            )
        )

        val response = repository.signIn(SignIn(email, password))

        verify(newsApi).postSignIn(SignInRequest(email, password))
        assertEquals(Result.Error(ErrorEntity.Unknown), response)
    }

    @Test
    fun signUp_returnValidToken() = runBlockingTest {
        val signUpRequest = SignUpRequest(name, email, password)

        whenever(newsApi.postSignUp(signUpRequest))
            .thenReturn(Response.success(TokenResponse(token)))

        val response = repository.signUp(SignUp(name, email, password))

        verify(newsApi).postSignUp(signUpRequest)
        assertEquals(Result.Success(Token(token)), response)
    }

    @Test
    fun signUp_returnNotFoundError() = runBlockingTest {
        val signUpRequest = SignUpRequest(name, email, password)

        whenever(newsApi.postSignUp(signUpRequest))
            .thenReturn(
                Response.error(
                    404,
                    errorResponseBody.toResponseBody("application/json".toMediaTypeOrNull())
                )
            )

        val response = repository.signUp(SignUp(name, email, password))

        verify(newsApi).postSignUp(SignUpRequest(name, email, password))
        assertEquals(Result.Error(ErrorEntity.NotFound), response)
    }

    @Test
    fun signUp_returnAccessDeniedError() = runBlockingTest {
        val signUpRequest = SignUpRequest(name, email, password)

        whenever(newsApi.postSignUp(signUpRequest))
            .thenReturn(
                Response.error(
                    403,
                    errorResponseBody.toResponseBody("application/json".toMediaTypeOrNull())
                )
            )

        val response = repository.signUp(SignUp(name, email, password))

        verify(newsApi).postSignUp(SignUpRequest(name, email, password))
        assertEquals(Result.Error(ErrorEntity.AccessDenied), response)
    }

    @Test
    fun signUp_returnUnauthorizedError() = runBlockingTest {
        val signUpRequest = SignUpRequest(name, email, password)

        whenever(newsApi.postSignUp(signUpRequest))
            .thenReturn(
                Response.error(
                    401,
                    errorResponseBody.toResponseBody("application/json".toMediaTypeOrNull())
                )
            )

        val response = repository.signUp(SignUp(name, email, password))

        verify(newsApi).postSignUp(SignUpRequest(name, email, password))
        assertEquals(Result.Error(ErrorEntity.Unauthorized), response)
    }

    @Test
    fun signUp_returnServiceUnavailableError() = runBlockingTest {
        val signUpRequest = SignUpRequest(name, email, password)

        whenever(newsApi.postSignUp(signUpRequest))
            .thenReturn(
                Response.error(
                    503,
                    errorResponseBody.toResponseBody("application/json".toMediaTypeOrNull())
                )
            )

        val response = repository.signUp(SignUp(name, email, password))

        verify(newsApi).postSignUp(SignUpRequest(name, email, password))
        assertEquals(Result.Error(ErrorEntity.ServiceUnavailable), response)
    }

    @Test
    fun signUp_returnUnknownError() = runBlockingTest {
        val signUpRequest = SignUpRequest(name, email, password)

        whenever(newsApi.postSignUp(signUpRequest))
            .thenReturn(
                Response.error(
                    400,
                    errorResponseBody.toResponseBody("application/json".toMediaTypeOrNull())
                )
            )

        val response = repository.signUp(SignUp(name, email, password))

        verify(newsApi).postSignUp(SignUpRequest(name, email, password))
        assertEquals(Result.Error(ErrorEntity.Unknown), response)
    }

    companion object {
        private val name = "felipe"
        private val email = "felipe.faustini@email.com"
        private val password = "123"
        private val token = "123"
        private val errorResponseBody =
            "{\"errors\":[{\"code\":\"BLANK\",\"field\":\"password\",\"message\":\"Password can't be blank\"}]}"
    }

}