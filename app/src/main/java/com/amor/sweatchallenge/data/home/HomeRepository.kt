package com.amor.sweatchallenge.data.home

import com.amor.sweatchallenge.data.GenericData
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.database.user.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface HomeRepository {

    fun getProfilesPictures(page: Int): Single<GenericData<ArrayList<ProfileData>>>

    fun observerUsersOnDatabase(): Flowable<ArrayList<ProfileData>>

    fun saveUser(user: ProfileData): Completable

    fun deleteUser(userId: String): Completable
}