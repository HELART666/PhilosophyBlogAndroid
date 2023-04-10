package com.example.philosophyblog.data.repository

import com.example.philosophyblog.data.api.RemoteDataSource
import com.example.philosophyblog.data.api.model.signUp.SignUpRequest
import com.example.philosophyblog.data.api.model.signUp.SignUpResponse
import com.example.philosophyblog.utils.BaseApiResponse
import com.example.philosophyblog.utils.ScreenState
import javax.inject.Inject

class SignUpRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): BaseApiResponse() {
    suspend fun sendSignUpRequest(body: SignUpRequest): ScreenState<SignUpResponse> {
        return safeApiCall {
            remoteDataSource.sendSignUpRequest(
                body = body
            )
        }
    }
}