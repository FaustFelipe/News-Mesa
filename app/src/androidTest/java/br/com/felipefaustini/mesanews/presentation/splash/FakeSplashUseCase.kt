package br.com.felipefaustini.mesanews.presentation.splash

import br.com.felipefaustini.domain.usecases.splash.ISplashUseCase

class FakeSplashUseCase: ISplashUseCase {

    var isSignedIn = true

    override fun isUserSignedIn(): Boolean {
        return isSignedIn
    }

}