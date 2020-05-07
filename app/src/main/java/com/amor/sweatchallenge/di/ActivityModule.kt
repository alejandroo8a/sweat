package com.amor.sweatchallenge.di

import com.amor.sweatchallenge.presentation.home.HomeViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val ActivityModule = module {

    // HomeViewModel ViewModel
    viewModel { HomeViewModel(get()) }

    single { createHomeRepository(get()) }

    single { createHomeClient(get(), createHomeMapper()) }

    single { createPaginationUtil() }

}