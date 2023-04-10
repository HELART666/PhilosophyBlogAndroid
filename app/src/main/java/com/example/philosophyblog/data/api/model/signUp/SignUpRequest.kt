package com.example.philosophyblog.data.api.model.signUp

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("login")
    val login: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
