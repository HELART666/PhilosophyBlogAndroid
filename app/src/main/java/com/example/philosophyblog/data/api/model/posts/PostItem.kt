package com.example.philosophyblog.data.api.model.posts

import com.google.gson.annotations.SerializedName

data class PostItem(
    @SerializedName("_id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("imgUrl")
    val imgUrl: String? = null,
    @SerializedName("description")
    val description: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("tags")
    val tags: List<String>? = null,
    @SerializedName("viewsCount")
    val viewsCount: String,
    @SerializedName("user")
    val user: PostUserInfo,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)
