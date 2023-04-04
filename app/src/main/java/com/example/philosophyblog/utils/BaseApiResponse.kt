package com.example.philosophyblog.utils

import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(api: suspend () -> Response<T>): ScreenState<T> {
        try {
            val response = api()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ScreenState.Success(body)
                } ?: return errorMessage("Body is empty")
            } else {
                return errorMessage("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return errorMessage(e.message.toString())
        }
    }

    private fun <T> errorMessage(e: String): ScreenState<T> {
        return ScreenState.Error(data = null, message = "Error: $e")
    }
}