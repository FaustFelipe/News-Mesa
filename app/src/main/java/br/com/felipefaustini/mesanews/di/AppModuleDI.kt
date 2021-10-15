package br.com.felipefaustini.mesanews.di

import br.com.felipefaustini.mesanews.presentation.home.HomeViewModel
import br.com.felipefaustini.mesanews.presentation.signin.SignInViewModel
import br.com.felipefaustini.mesanews.presentation.signup.SignUpViewModel
import br.com.felipefaustini.mesanews.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModulesModule = module {
    viewModel { SignInViewModel(useCase = get()) }
    viewModel { SignUpViewModel(useCase = get()) }
    viewModel { HomeViewModel(useCase = get()) }
    viewModel { SplashViewModel(useCase = get()) }
}