package com.example.philosophyblog.data.api.model.posts

import com.google.gson.annotations.SerializedName

data class PostUserInfo(
    @SerializedName("_id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatarUrl")
    val avatarUrl: String? = null,
    @SerializedName("posts")
    val posts: List<String>? = null,
)
