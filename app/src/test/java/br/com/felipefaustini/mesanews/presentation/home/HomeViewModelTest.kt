package br.com.felipefaustini.mesanews.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.felipefaustini.domain.models.News
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.usecases.home.HomeUseCase
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.mesanews.MainCoroutineRule
import br.com.felipefaustini.mesanews.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: NewsRepository

    private lateinit var homeUseCase: HomeUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun beforeEachTest() {
        homeUseCase = HomeUseCase(repository)
        homeViewModel = HomeViewModel(homeUseCase)
    }

    @Test
    fun listNews_shouldMakeNewsRequestAndReturnListOfNews() = runBlockingTest {
        val expected = listOf<News>(NEWS)

        whenever(repository.getNews())
            .thenReturn(Result.Success(listOf(NEWS)))

        homeViewModel.listNews()

        val result = homeViewModel.listNewsLiveData.getOrAwaitValue()

        assertEquals(expected, result)
    }

    @Test
    fun listNews_shouldMakeNewsRequestAndShowError() = runBlockingTest {
        val expected = "Erro"

        whenever(repository.getNews())
            .thenReturn(Result.Error(ErrorEntity.Unknown))

        homeViewModel.listNews()

        val result = homeViewModel.errorMessageLiveData.getOrAwaitValue()

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