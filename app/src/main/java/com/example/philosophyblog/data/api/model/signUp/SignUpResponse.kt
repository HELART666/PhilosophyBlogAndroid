package com.example.philosophyblog.data.api.model.signUp

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("userId")
    val userId: String,
)
