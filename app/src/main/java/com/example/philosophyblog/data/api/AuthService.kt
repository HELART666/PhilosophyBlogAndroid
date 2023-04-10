package com.example.philosophyblog.data.api

import com.example.philosophyblog.data.api.model.auth.AuthRequest
import com.example.philosophyblog.data.api.model.auth.AuthResponse
import com.example.philosophyblog.data.api.model.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @POST("api/authorization")
    suspend fun sendAuthRequest(@Body body: AuthRequest): Response<AuthResponse>

    @GET("api/refresh")
    suspend fun getNewTokens(): Response<RefreshTokenResponse>

}