package com.example.philosophyblog.data.api.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("accessToken")
    val accessToken: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("refreshToken")
    val refreshToken: String? = null,
    @SerializedName("userId")
    val userId: String? = null,
    @SerializedName("login")
    val login: String? = null
)