package com.amor.sweatchallenge.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.database.USER_TABLE_NAME
import com.amor.sweatchallenge.network.DomainMapper
import java.util.*

@Entity(tableName = USER_TABLE_NAME)
data class UserEntity (
    val thumbnail: String,
    val largeImage: String,
    val name: String,
    val phone: String,
    val email: String,
    val location: String,
    val latitude: String,
    val longitude: String,
    var isFavorite: Boolean,
    @PrimaryKey @ColumnInfo(name = "userid")val userId: String = UUID.randomUUID().toString()
): DomainMapper<ProfileData> {

    override fun mapToDomainModel(): ProfileData {
        return ProfileData(
            thumbnail,
            largeImage,
            name,
            phone,
            email,
            location,
            latitude,
            longitude,
            isFavorite,
            userId
        )
    }

}