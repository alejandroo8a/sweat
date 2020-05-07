package com.amor.sweatchallenge.data.home

import com.amor.sweatchallenge.data.GenericData
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.network.home.HomeClient
import io.reactivex.Single

class DefaultHomeRepository (private val homeClient: HomeClient) : HomeRepository {

    override fun getProfilesPictures(page: Int): Single<GenericData<ArrayList<ProfileData>>> {
        return homeClient.profilePicturesRequest(page)
    }

}