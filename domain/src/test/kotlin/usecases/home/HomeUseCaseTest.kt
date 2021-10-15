package usecases.home

import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.usecases.home.HomeUseCase
import br.com.felipefaustini.domain.utils.Result
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class HomeUseCaseTest {

    @Mock
    private lateinit var repository: NewsRepository

    private lateinit var useCase: HomeUseCase

    @Before
    fun beforeEachTest() {
        useCase = HomeUseCase(repository)
    }

    @Test
    fun getNews_returnListOfNews() = runBlockingTest {
        val expected = Result.Success(listOf(NEWS))

        whenever(repository.getNews())
            .thenReturn(Result.Success(listOf(NEWS)))

        val result = useCase.getNews()

        verify(repository).getNews()
        verifyNoMoreInteractions(repository)
        assertEquals(expected, result)
    }

    companion object {
        private val NEWS = News(
            title = "Title",
            description = "Description",
            content = "Content",
            author = "Felipe",
            publishedAt = "",
            highlight = false,
            url = "",
            imageUrl = ""
        )
    }

}