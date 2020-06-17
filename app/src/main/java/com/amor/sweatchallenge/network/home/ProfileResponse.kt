package com.amor.sweatchallenge.network.home

data class ProfileResponse (
    val phone: String,
    val email: String,
    val picture: PictureResponse,
    val name: NameResponse,
    val location: LocationResponse
)