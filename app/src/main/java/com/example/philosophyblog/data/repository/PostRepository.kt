package com.example.philosophyblog.data.repository

import com.example.philosophyblog.data.api.RemoteDataSource
import com.example.philosophyblog.data.api.model.posts.Posts
import com.example.philosophyblog.utils.BaseApiResponse
import com.example.philosophyblog.utils.ScreenState
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : BaseApiResponse() {

    suspend fun getAllPosts(): ScreenState<Posts> {
        return safeApiCall {
            remoteDataSource.getAllPosts()
        }
    }
}