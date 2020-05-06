package com.amor.sweatchallenge.di

val ActivityModule = module {

    // PostsViewModel ViewModel
    viewModel { PostsViewModel(get()) }

    single { createGetPostsUseCase(get(), createApiErrorHandle()) }

    // single instance of PostsRepository
    single { createPostRepository(get()) }
}