package br.com.felipefaustini.domain.di

import br.com.felipefaustini.domain.usecases.home.HomeUseCase
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import br.com.felipefaustini.domain.usecases.signin.ISignInUseCase
import br.com.felipefaustini.domain.usecases.signin.SignInUseCase
import br.com.felipefaustini.domain.usecases.signup.ISignUpUseCase
import br.com.felipefaustini.domain.usecases.signup.SignUpUseCase
import br.com.felipefaustini.domain.usecases.splash.ISplashUseCase
import br.com.felipefaustini.domain.usecases.splash.SplashUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single<ISignInUseCase> { SignInUseCase(repository = get()) }
    single<ISignUpUseCase> { SignUpUseCase(repository = get()) }
    single<ISplashUseCase> { SplashUseCase(repository = get()) }
    single<IHomeUseCase> { HomeUseCase(repository = get()) }
}