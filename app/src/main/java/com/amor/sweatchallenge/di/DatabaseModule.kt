package com.amor.sweatchallenge.di

import android.content.Context
import androidx.room.Room
import com.amor.sweatchallenge.database.APP_DATABASE_NAME
import com.amor.sweatchallenge.database.AppDatabase
import com.amor.sweatchallenge.database.user.DefaultUserDataSource
import com.amor.sweatchallenge.database.user.UserDao
import com.amor.sweatchallenge.database.user.UserDataSource
import org.koin.dsl.module

val DatabaseModule = module {

    single { createUserDataSource(get()) }

    single { createAppDatabase(get()) }

    single { createUserDao(get()) }

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