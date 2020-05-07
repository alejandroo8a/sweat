package com.amor.sweatchallenge.network

import com.amor.sweatchallenge.network.home.GeneralProfileResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    fun getProfilesPictures(
        @Query("page") page: Int,
        @Query("results") result: Int = 50
    ): Single<GeneralProfileResponse>

}