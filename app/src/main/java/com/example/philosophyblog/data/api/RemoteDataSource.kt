package com.example.philosophyblog.data.api

import com.example.philosophyblog.data.api.model.AuthRequest
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val authService: AuthService) {

    suspend fun sendAuthRequest(body: AuthRequest) = authService.sendAuthRequest(body = body)



}