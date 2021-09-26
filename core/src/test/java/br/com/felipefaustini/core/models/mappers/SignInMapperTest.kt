package br.com.felipefaustini.core.models.mappers

import br.com.felipefaustini.core.models.request.SignInRequest
import br.com.felipefaustini.domain.models.SignIn
import org.junit.Assert.assertEquals
import org.junit.Test

class SignInMapperTest {

    @Test
    fun map_SignInRequest() {
        val signIn = SignIn(email = "email", password = "123")

        val response = SignInMapper.map(signIn)

        assertEquals(SignInRequest("email", "123"), response)
    }

}