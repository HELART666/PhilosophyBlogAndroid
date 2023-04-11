package com.example.philosophyblog.data.api.model.user

import com.google.gson.annotations.SerializedName

data class NewUserData(
    @SerializedName("avatarUrl")
    val avatarUrl: String? = null,
    @SerializedName("bio")
    val bio: Bio,
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("role")
    val role: String? = null

)
