package com.example.philosophyblog.data.api

import com.example.philosophyblog.data.api.model.AuthRequest
import com.example.philosophyblog.data.api.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("authorization")
    suspend fun sendAuthRequest(@Body body: AuthRequest): Response<AuthResponse>

}