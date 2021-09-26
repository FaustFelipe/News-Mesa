package br.com.felipefaustini.domain.di

import br.com.felipefaustini.domain.usecases.ISignInUseCase
import br.com.felipefaustini.domain.usecases.SignInUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single<ISignInUseCase> { SignInUseCase(repository = get()) }
}