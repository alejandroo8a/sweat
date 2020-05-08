package com.amor.sweatchallenge.data.home

import com.amor.sweatchallenge.data.GenericData
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.database.user.UserEntity
import com.amor.sweatchallenge.network.ResponseConversions
import com.amor.sweatchallenge.network.home.GeneralProfileResponse

class HomeMapper {

    fun toHomeProfileData(response: GeneralProfileResponse) = GenericData(toProfileData(response))
    fun toHomeProfileData(throwable: Throwable) = GenericData<ArrayList<ProfileData>>(ResponseConversions.toNetworkResult(throwable))

    fun toProfileData(userList: List<UserEntity>) = toProfileDataFromDatabase(userList)

    private fun toProfileData(response: GeneralProfileResponse): ArrayList<ProfileData> {
        val profileList = arrayListOf<ProfileData>()
        for (profile in response.results) {
            profileList.add(
                ProfileData(
                    profile.picture.thumbnail,
                    profile.picture.large,
                    "${profile.name.first} ${profile.name.last}",
                    profile.phone,
                    profile.email,
                    "${profile.location.state}, ${profile.location.country}",
                    profile.location.coordinates.latitude,
                    profile.location.coordinates.longitude
                )
            )
        }
        return profileList
    }

    private fun toProfileDataFromDatabase(userList: List<UserEntity>): ArrayList<ProfileData> {
        val profilesList = arrayListOf<ProfileData>()
        for (user in userList) {
            profilesList.add(user.mapToDomainModel())
        }
        return profilesList
    }
}