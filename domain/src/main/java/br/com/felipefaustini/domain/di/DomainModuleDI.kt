package br.com.felipefaustini.domain.di

import br.com.felipefaustini.domain.usecases.signin.ISignInUseCase
import br.com.felipefaustini.domain.usecases.signin.SignInUseCase
import br.com.felipefaustini.domain.usecases.signup.ISignUpUseCase
import br.com.felipefaustini.domain.usecases.signup.SignUpUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single<ISignInUseCase> { SignInUseCase(repository = get()) }
    single<ISignUpUseCase> { SignUpUseCase(repository = get()) }
}