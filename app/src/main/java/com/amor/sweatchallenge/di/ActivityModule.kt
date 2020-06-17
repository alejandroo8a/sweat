package com.amor.sweatchallenge.di

import com.amor.sweatchallenge.data.home.DefaultHomeRepository
import com.amor.sweatchallenge.data.home.HomeMapper
import com.amor.sweatchallenge.data.home.HomeRepository
import com.amor.sweatchallenge.database.user.UserDataSource
import com.amor.sweatchallenge.network.ApiService
import com.amor.sweatchallenge.network.home.HomeClient
import com.amor.sweatchallenge.util.NetworkUtil
import com.amor.sweatchallenge.util.SearchViewUtil
import com.amor.sweatchallenge.util.pagination.PaginationUtil
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val ActivityModule = module {

    single { createHomeRepository(get(), createUserDataSource(createUserDao(createAppDatabase(androidContext()))), createHomeMapper()) }

    single { createHomeClient(get(), createHomeMapper()) }

    single { createPaginationUtil() }

    single { createSearchViewUtil(get()) }

    single { createNetworkUtil() }

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

fun createSearchViewUtil(paginationUtil: PaginationUtil): SearchViewUtil {
    return SearchViewUtil(paginationUtil)
}

fun createNetworkUtil(): NetworkUtil {
    return NetworkUtil()
}