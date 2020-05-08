package com.amor.sweatchallenge.network.home

data class LocationResponse (
    val city: String,
    val state: String,
    val country: String,
    val coordinates: LocationCoordinatesResponse
)