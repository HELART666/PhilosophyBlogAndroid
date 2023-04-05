package com.example.philosophyblog.data.api.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)