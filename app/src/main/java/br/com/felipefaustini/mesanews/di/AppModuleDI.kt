package br.com.felipefaustini.mesanews.di

import br.com.felipefaustini.mesanews.presentation.signin.SignInViewModule
import br.com.felipefaustini.mesanews.presentation.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModulesModule = module {
    viewModel { SignInViewModule(useCase = get()) }
    viewModel { SignUpViewModel() }
}