package com.amor.sweatchallenge.network.home

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeneralProfileResponse (
    val results: List<ProfileResponse>,
    val info: InfoResponse
)