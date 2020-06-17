package com.amor.sweatchallenge.network

import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.SocketException
import java.net.UnknownHostException

class ResponseConversions {
    companion object {
        private fun isConnectionException(throwable: Throwable): Boolean {
            return (throwable is HttpException && isConnectionException(throwable)
                    || throwable is UnknownHostException
                    || throwable is InterruptedIOException
                    || throwable is SocketException)
        }

        private fun isConnectionException(httpException: HttpException): Boolean {
            return httpException.code() == HTTP_REQUEST_TIMEOUT
        }

        private fun isAuthorizationException(throwable: Throwable): Boolean {
            return throwable is HttpException && throwable.code() == HTTP_UNAUTHORIZED
        }

        private fun isForbiddenException(throwable: Throwable): Boolean {
            return throwable is HttpException && throwable.code() == HTTP_FORBIDDEN
        }

        private fun isNotFoundException(throwable: Throwable): Boolean {
            return throwable is HttpException && throwable.code() == HTTP_NOT_FOUND
        }

        fun toNetworkResult(throwable: Throwable): NetworkResult {
            return when {
                isConnectionException(throwable) -> NetworkResult.CONNECTION_ERROR
                isAuthorizationException(throwable) -> NetworkResult.AUTHORIZATION_ERROR
                isForbiddenException(throwable) -> NetworkResult.FORBIDDEN_ERROR
                isNotFoundException(throwable) -> NetworkResult.NOT_FOUND_ERROR
                else -> NetworkResult.GENERIC_ERROR
            }
        }

        private const val HTTP_REQUEST_TIMEOUT = 408
        private const val HTTP_NOT_FOUND = 404
        private const val HTTP_FORBIDDEN = 403
        private const val HTTP_UNAUTHORIZED = 401
    }
}