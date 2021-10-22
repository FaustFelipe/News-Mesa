package br.com.felipefaustini.mesanews.presentation.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.felipefaustini.domain.usecases.splash.SplashUseCase
import br.com.felipefaustini.mesanews.MainCoroutineRule
import br.com.felipefaustini.mesanews.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
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
    private lateinit var useCase: SplashUseCase

    @InjectMocks
    private lateinit var viewModel: SplashViewModel

    @Test
    fun openHome_shouldOpenOnboarding() {
        val expected = Unit

        whenever(useCase.isUserSignedIn())
            .thenReturn(false)

        viewModel.openHome()

        val response = viewModel.navigateToOnboarding.getOrAwaitValue()

        assertEquals(expected, response)
    }

    @Test
    fun openHome_shouldOpenHome() {
        val expected = Unit

        whenever(useCase.isUserSignedIn())
            .thenReturn(true)

        viewModel.openHome()

        val response = viewModel.navigateToHome.getOrAwaitValue()

        assertEquals(expected, response)
    }

}