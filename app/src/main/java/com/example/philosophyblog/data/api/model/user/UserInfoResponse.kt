package com.example.philosophyblog.data.api.model.user

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("bio")
    val bio: Bio? = null,
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("role")
    val role: String? = null,
    @SerializedName("avatarUrl")
    val avatarUrl: String? = null,
)