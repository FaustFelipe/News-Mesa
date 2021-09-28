package br.com.felipefaustini.core.models.mappers

import br.com.felipefaustini.core.models.request.SignUpRequest
import br.com.felipefaustini.domain.models.SignUp

object SignUpMapper {

    fun map(signUp: SignUp) = SignUpRequest(
        name = signUp.name,
        email = signUp.email,
        password = signUp.password
    )

}