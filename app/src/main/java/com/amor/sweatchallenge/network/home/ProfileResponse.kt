package com.amor.sweatchallenge.network.home

data class ProfileResponse (
    val cell: String,
    val picture: PictureResponse,
    val name: NameResponse
)