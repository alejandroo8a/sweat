package com.amor.sweatchallenge.di

import com.amor.sweatchallenge.presentation.home.HomeViewModel
import org.koin.dsl.module
import com.amor.sweatchallenge.presentation.home.detail.DetailViewModel

import org.koin.android.viewmodel.dsl.viewModel

val ViewModelModule = module {

    viewModel { HomeViewModel(get()) }

    viewModel { DetailViewModel(get()) }

}