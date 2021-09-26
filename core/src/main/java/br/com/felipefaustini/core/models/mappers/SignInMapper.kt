package br.com.felipefaustini.core.models.mappers

import br.com.felipefaustini.core.models.request.SignInRequest
import br.com.felipefaustini.domain.models.SignIn

object SignInMapper {

    fun map(signIn: SignIn) = SignInRequest(
        email = signIn.email,
        password = signIn.password
    )

}