package br.com.felipefaustini.mesanews.presentation.splash

import androidx.lifecycle.LiveData
import br.com.felipefaustini.domain.usecases.splash.ISplashUseCase
import br.com.felipefaustini.mesanews.presentation.BaseViewModel
import br.com.felipefaustini.mesanews.utils.EventLiveData

class SplashViewModel(
    private val useCase: ISplashUseCase
): BaseViewModel() {

    private val _navigateToOnboarding = EventLiveData<Unit>()
    val navigateToOnboarding: LiveData<Unit> = _navigateToOnboarding

    private val _navigateToHome = EventLiveData<Unit>()
    val navigateToHome: LiveData<Unit> = _navigateToHome

    fun openHome() {
        when(val result = useCase.isUserSignedIn()) {
            true -> _navigateToHome.postValue(Unit)
            false -> _navigateToOnboarding.postValue(Unit)
        }
    }

}