package com.amor.sweatchallenge.data

import com.amor.sweatchallenge.database.user.UserEntity
import com.amor.sweatchallenge.network.RoomMapper

data class ProfileData(
    val thumbnail: String,
    val largeImage: String,
    val name: String,
    val phone: String,
    val email: String,
    val location: String,
    val latitude: String,
    val longitude: String,
    var isFavorite: Boolean = false,
    var userId: String = ""
) : RoomMapper<UserEntity> {

    override fun mapToRoomEntity(): UserEntity {
        return UserEntity(
            thumbnail,
            largeImage,
            name,
            phone,
            email,
            location,
            latitude,
            longitude,
            isFavorite
        )
    }

}