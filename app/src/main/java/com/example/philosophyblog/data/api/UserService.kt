package com.example.philosophyblog.data.api

import com.example.philosophyblog.data.api.model.user.UserInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface UserService {

    @GET
    suspend fun getUserInfo(@Url url: String) : Response<UserInfoResponse>
}