package com.amor.sweatchallenge.network.home

import com.amor.sweatchallenge.data.GenericData
import com.amor.sweatchallenge.data.home.HomeMapper
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.network.ApiService
import io.reactivex.Single

class HomeClient constructor(private val apiService: ApiService, private val mapper: HomeMapper) {

    fun profilePicturesRequest(page: Int): Single<GenericData<ArrayList<ProfileData>>> {
        return apiService.getProfilesPictures(page)
            .map(mapper::toHomeProfileData)
            .onErrorReturn(mapper::toHomeProfileData)
            .doOnError { error -> error.printStackTrace() }
    }

}