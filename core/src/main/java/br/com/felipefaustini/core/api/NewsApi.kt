package br.com.felipefaustini.core.api

import br.com.felipefaustini.core.models.request.SignInRequest
import br.com.felipefaustini.core.models.response.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NewsApi {
    @POST("v1/client/auth/signin")
    suspend fun postSignIn(
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>
}