package br.com.felipefaustini.domain.di

import br.com.felipefaustini.domain.usecases.signin.SignInUseCase
import br.com.felipefaustini.domain.usecases.signup.SignUpUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { SignInUseCase(repository = get()) }
    single { SignUpUseCase(repository = get()) }
}