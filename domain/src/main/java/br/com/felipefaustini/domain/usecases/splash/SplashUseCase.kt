package br.com.felipefaustini.domain.usecases.splash

import br.com.felipefaustini.domain.repository.NewsRepository

class SplashUseCase(
    private val repository: NewsRepository
) {

    fun isUserSignedIn(): Boolean {
        return repository.isUserSidnedIn()
    }

}