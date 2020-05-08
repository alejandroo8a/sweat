package com.amor.sweatchallenge.di

import com.amor.sweatchallenge.presentation.home.HomeViewModel
import com.amor.sweatchallenge.presentation.home.detail.DetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val ActivityModule = module {

    // HomeViewModel ViewModel
    viewModel { HomeViewModel(get()) }

    viewModel { DetailViewModel(get()) }

    single { createHomeRepository(get(), createUserDataSource(createUserDao(createAppDatabase(androidContext()))), createHomeMapper()) }

    single { createHomeClient(get(), createHomeMapper()) }

    single { createPaginationUtil() }

}