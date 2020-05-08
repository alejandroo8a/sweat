package com.amor.sweatchallenge.data

data class ProfileData(
    val thumbnail: String,
    val largeImage: String,
    val name: String,
    val phone: String,
    val email: String,
    val location: String,
    val latitude: String,
    val longitude: String,
    var isFavorite: Boolean = false
)