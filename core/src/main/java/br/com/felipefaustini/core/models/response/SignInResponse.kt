package br.com.felipefaustini.core.models.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInResponse(
    val token: String
)
