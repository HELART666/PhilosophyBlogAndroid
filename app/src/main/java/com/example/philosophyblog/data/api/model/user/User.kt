package com.example.philosophyblog.data.api.model.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String,
    @SerializedName("bio")
    val bio: Bio
)
