package br.com.felipefaustini.mesanews.di

import br.com.felipefaustini.mesanews.presentation.signin.SignInViewModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModulesModule = module {
    viewModel { SignInViewModule(useCase = get()) }
}