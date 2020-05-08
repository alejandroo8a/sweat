package com.amor.sweatchallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amor.sweatchallenge.database.user.UserDao
import com.amor.sweatchallenge.database.user.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}