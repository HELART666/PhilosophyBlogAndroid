package com.example.philosophyblog.data.api

import com.example.philosophyblog.data.api.model.signUp.SignUpRequest
import com.example.philosophyblog.data.api.model.signUp.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {

    @POST("api/registration")
    suspend fun sendSignUpRequest(@Body body: SignUpRequest): Response<SignUpResponse>
}