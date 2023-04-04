package com.example.philosophyblog.data.repository

import com.example.philosophyblog.data.api.RemoteDataSource
import com.example.philosophyblog.data.api.model.AuthRequest
import com.example.philosophyblog.data.api.model.AuthResponse
import com.example.philosophyblog.utils.BaseApiResponse
import com.example.philosophyblog.utils.ScreenState
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : BaseApiResponse() {

    suspend fun sendAuthRequest(body: AuthRequest): ScreenState<AuthResponse> {
        return safeApiCall {
            remoteDataSource.sendAuthRequest(body = body)
        }
    }

}