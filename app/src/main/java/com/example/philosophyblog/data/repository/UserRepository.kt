package com.example.philosophyblog.data.repository

import com.example.philosophyblog.data.api.RemoteDataSource
import com.example.philosophyblog.data.api.model.user.UserInfoResponse
import com.example.philosophyblog.utils.BaseApiResponse
import com.example.philosophyblog.utils.ScreenState
import okhttp3.MultipartBody
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : BaseApiResponse() {

    suspend fun getUserInfo(url: String): ScreenState<UserInfoResponse> {
        return safeApiCall {
            remoteDataSource.getUserInfo(
                url = url
            )
        }
    }

    suspend fun updateUserInfo(
        url: String,
        avatar: MultipartBody.Part,
        formUserData: MultipartBody.Part
    ): ScreenState<UserInfoResponse> {
        return safeApiCall {
            remoteDataSource.updateUserInfo(
                url = url,
                avatar = avatar,
                formUserData = formUserData
            )
        }
    }

}