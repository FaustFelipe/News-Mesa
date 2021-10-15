package usecases.splash

import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.usecases.splash.SplashUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class SplashUseCaseTest {

    @Mock
    private lateinit var repository: NewsRepository

    private lateinit var useCase: SplashUseCase

    @Before
    fun beforeEachTest() {
        useCase = SplashUseCase(repository)
    }

    @Test
    fun isUserSignedIn_shouldReturnFalseForEmptyToken() {
        val expected = false
        whenever(repository.isUserSidnedIn())
            .thenReturn(false)

        val result = useCase.isUserSignedIn()

        assertEquals(expected, result)
    }

    @Test
    fun isUserSignedIn_shouldReturnTrueForValidToken() {
        val expected = true
        whenever(repository.isUserSidnedIn())
            .thenReturn(true)

        val result = useCase.isUserSignedIn()

        assertEquals(expected, result)
    }

}