package com.amor.sweatchallenge.data.home

import com.amor.sweatchallenge.data.GenericData
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.database.user.UserDataSource
import com.amor.sweatchallenge.network.home.HomeClient
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class DefaultHomeRepository (
    private val homeClient: HomeClient,
    private val defaultUserDataSource: UserDataSource,
    private val mapper: HomeMapper
) : HomeRepository {

    override fun getProfilesPictures(page: Int): Single<GenericData<ArrayList<ProfileData>>> {
        return homeClient.profilePicturesRequest(page)
    }

    override fun observerUsersOnDatabase(): Flowable<ArrayList<ProfileData>> {
        return defaultUserDataSource.observeUser()
            .map(mapper::toProfileData)
    }

    override fun saveUser(user: ProfileData): Completable {
        return defaultUserDataSource.saveUser(user.mapToRoomEntity())
    }

    override fun deleteUser(userId: String): Completable {
        return defaultUserDataSource.deleteUser(userId)
    }

}