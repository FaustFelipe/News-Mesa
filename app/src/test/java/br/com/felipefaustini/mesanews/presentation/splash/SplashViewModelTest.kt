package br.com.felipefaustini.mesanews.presentation.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.felipefaustini.domain.repository.NewsRepository
import br.com.felipefaustini.domain.usecases.splash.SplashUseCase
import br.com.felipefaustini.mesanews.MainCoroutineRule
import br.com.felipefaustini.mesanews.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class SplashViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: NewsRepository

    private lateinit var useCase: SplashUseCase

    private lateinit var viewModel: SplashViewModel

    @Before
    fun beforeEachTest() {
        useCase = SplashUseCase(repository)

        viewModel = SplashViewModel(useCase)
    }

    @Test
    fun openHome_shouldOpenOnboarding() {
        val expected = Unit

        whenever(repository.isUserSidnedIn())
            .thenReturn(false)

        viewModel.openHome()

        val response = viewModel.navigateToOnboarding.getOrAwaitValue()

        assertEquals(expected, response)
    }

    @Test
    fun openHome_shouldOpenHome() {
        val expected = Unit

        whenever(repository.isUserSidnedIn())
            .thenReturn(true)

        viewModel.openHome()

        val response = viewModel.navigateToHome.getOrAwaitValue()

        assertEquals(expected, response)
    }

}