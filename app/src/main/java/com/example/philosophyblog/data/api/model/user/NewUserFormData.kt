package com.example.philosophyblog.data.api.model.user

import com.google.gson.annotations.SerializedName

data class NewUserFormData(
    @SerializedName("oldLogin")
    val oldLogin: String,
    @SerializedName("user")
    val user: User
)