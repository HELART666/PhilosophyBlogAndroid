package com.example.philosophyblog.data.api

import com.example.philosophyblog.data.api.model.auth.AuthRequest
import com.example.philosophyblog.data.api.model.signUp.SignUpRequest
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val authService: AuthService,
    private val signUpService: SignUpService,
    private val userService: UserService
) {

    suspend fun sendAuthRequest(body: AuthRequest) = authService.sendAuthRequest(body = body)

    suspend fun sendSignUpRequest(body: SignUpRequest) = signUpService.sendSignUpRequest(body = body)

    suspend fun getUserInfo(url: String) = userService.getUserInfo(url = url)
}