package com.example.philosophyblog.data.api.model.user

import com.google.gson.annotations.SerializedName

data class ShortUserResponse(
    @SerializedName("bio")
    val bio: Bio,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatarUrl")
    val avatarUrl: String?
)
