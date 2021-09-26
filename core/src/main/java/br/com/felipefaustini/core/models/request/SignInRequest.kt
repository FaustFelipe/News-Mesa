package br.com.felipefaustini.core.models.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInRequest(
    val email: String,
    val password: String
)