package com.amor.sweatchallenge.network

enum class NetworkResult {
    SUCCESS,
    AUTHORIZATION_ERROR,
    FORBIDDEN_ERROR,
    NOT_FOUND_ERROR,
    CONNECTION_ERROR,
    GENERIC_ERROR,
    SERVER_ERROR
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}