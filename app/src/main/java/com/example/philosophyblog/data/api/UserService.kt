package com.example.philosophyblog.data.api

import com.example.philosophyblog.data.api.model.user.ShortUserResponse
import com.example.philosophyblog.data.api.model.user.UserInfoResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET
    suspend fun getUserInfo(@Url url: String): Response<UserInfoResponse>

    @Multipart
    @PATCH
    suspend fun updateUserInfo(
        @Url url: String,
        @Part avatar: MultipartBody.Part,
        @Part formUserData: MultipartBody.Part,
    ): Response<UserInfoResponse>

    @GET("api/users")
    suspend fun getAllUsers(): Response<List<ShortUserResponse>>

}