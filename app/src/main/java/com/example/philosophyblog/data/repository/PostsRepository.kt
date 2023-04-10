package com.example.philosophyblog.data.repository

import com.example.philosophyblog.data.api.RemoteDataSource
import com.example.philosophyblog.data.api.model.user.UserInfoResponse
import com.example.philosophyblog.utils.BaseApiResponse
import com.example.philosophyblog.utils.ScreenState
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : BaseApiResponse() {

    suspend fun getUserInfo(url: String): ScreenState<UserInfoResponse> {
        return safeApiCall {
            remoteDataSource.getUserInfo(
                url = url
            )
        }
    }
}