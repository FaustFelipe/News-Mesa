package br.com.felipefaustini.core

import br.com.felipefaustini.domain.Result
import retrofit2.http.Body
import retrofit2.http.POST

interface NewsApi {
    @POST("/v1/client/auth/signin")
    suspend fun postSignIn(@Body signInRequest: SignInRequest): Result<SignInResponse>
}