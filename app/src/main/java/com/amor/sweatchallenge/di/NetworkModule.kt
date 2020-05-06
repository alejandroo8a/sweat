package com.amor.sweatchallenge.di

import com.amor.sweatchallenge.BuildConfig
import com.amor.sweatchallenge.network.ApiErrorHandle
import com.amor.sweatchallenge.network.ApiService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.BASE_URL) }

    single { createOkHttpClient() }

    single { createMoshiConverterFactory() }

    single { createMoshi() }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}


fun createMoshi(): Moshi {
    return Moshi.Builder().build()
}

fun createMoshiConverterFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create()
}

fun createApiErrorHandle(): ApiErrorHandle {
    return ApiErrorHandle()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

//fun createPostRepository(apiService: ApiService): PostsRepository {
//    return PostsRepositoryImp(apiService)
//}
//
//fun createGetPostsUseCase(
//    postsRepository: PostsRepository,
//    apiErrorHandle: ApiErrorHandle
//): GetPostsUseCase {
//    return GetPostsUseCase(postsRepository, apiErrorHandle)
//}
