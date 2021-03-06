package br.com.felipefaustini.core.api

import br.com.felipefaustini.core.models.request.SignInRequest
import br.com.felipefaustini.core.models.request.SignUpRequest
import br.com.felipefaustini.core.models.response.NewsResponse
import br.com.felipefaustini.core.models.response.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NewsApi {

    @POST("v1/client/auth/signin")
    suspend fun postSignIn(
        @Body signInRequest: SignInRequest
    ): Response<TokenResponse>

    @POST("v1/client/auth/signup")
    suspend fun postSignUp(
        @Body signUpRequest: SignUpRequest
    ): Response<TokenResponse>

    @GET("v1/client/news/highlights")
    suspend fun getHighlights(

    ): Response<NewsResponse>

    @GET("v1/client/news")
    suspend fun getNews(

    ): Response<NewsResponse>

}