package com.amor.sweatchallenge.data.home

import com.amor.sweatchallenge.data.GenericData
import com.amor.sweatchallenge.data.ProfileData
import io.reactivex.Single

interface HomeRepository {

    fun getProfilesPictures(page: Int): Single<GenericData<ArrayList<ProfileData>>>

}