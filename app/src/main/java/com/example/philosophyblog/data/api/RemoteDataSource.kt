package com.example.philosophyblog.data.api

import com.example.philosophyblog.data.api.model.AuthRequest
import com.example.philosophyblog.data.api.model.AuthResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val authService: AuthService) {

    suspend fun sendAuthRequest(body: AuthRequest) = authService.sendAuthRequest(body = body)



}