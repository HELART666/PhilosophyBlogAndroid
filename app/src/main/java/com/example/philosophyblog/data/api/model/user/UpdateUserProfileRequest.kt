package com.example.philosophyblog.data.api.model.user

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class UpdateUserProfileRequest(
    @SerializedName("avatar")
    val avatar: MultipartBody.Part,
    @SerializedName("user")
    val user: MultipartBody.Part,
)
