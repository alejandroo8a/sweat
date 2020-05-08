package com.amor.sweatchallenge.database.user

import io.reactivex.Completable
import io.reactivex.Flowable

interface UserDataSource {

    fun observeUser(): Flowable<List<UserEntity>>

    fun saveUser(user: UserEntity): Completable

    fun deleteUser(userId: String): Completable
}