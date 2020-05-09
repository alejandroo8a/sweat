package com.amor.sweatchallenge.di

import android.content.Context
import androidx.room.Room
import com.amor.sweatchallenge.BuildConfig
import com.amor.sweatchallenge.data.home.DefaultHomeRepository
import com.amor.sweatchallenge.data.home.HomeMapper
import com.amor.sweatchallenge.data.home.HomeRepository
import com.amor.sweatchallenge.database.APP_DATABASE_NAME
import com.amor.sweatchallenge.database.AppDatabase
import com.amor.sweatchallenge.database.user.DefaultUserDataSource
import com.amor.sweatchallenge.database.user.UserDao
import com.amor.sweatchallenge.database.user.UserDataSource
import com.amor.sweatchallenge.network.ApiService
import com.amor.sweatchallenge.network.home.HomeClient
import com.amor.sweatchallenge.util.SearchViewUtil
import com.amor.sweatchallenge.util.pagination.PaginationUtil
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.BASE_URL, get()) }

    single { createOkHttpClient() }

    single { createMoshi() }

    single { createUserDataSource(get()) }

    single { createAppDatabase(get()) }

    single { createUserDao(get()) }

    single { createSearchViewUtil(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
}

fun createMoshi(): Moshi {
    return Moshi.Builder().build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createHomeMapper(): HomeMapper {
    return HomeMapper()
}

fun createHomeClient(apiService: ApiService, mapper: HomeMapper): HomeClient {
    return HomeClient(apiService, mapper)
}

fun createHomeRepository(
    client: HomeClient,
    userDataSource: UserDataSource,
    mapper: HomeMapper
): HomeRepository {
    return DefaultHomeRepository(client, userDataSource, mapper)
}

fun createPaginationUtil(): PaginationUtil {
    return PaginationUtil()
}

fun createUserDataSource(userDao: UserDao): UserDataSource {
    return DefaultUserDataSource(userDao)
}

fun createAppDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()
}

fun createUserDao(appDatabase: AppDatabase): UserDao {
    return appDatabase.userDao()
}

fun createSearchViewUtil(paginationUtil: PaginationUtil): SearchViewUtil {
    return SearchViewUtil(paginationUtil)
}
