package com.example.philosophyblog.data.repository

import com.example.philosophyblog.data.api.RemoteDataSource
import com.example.philosophyblog.data.api.model.posts.PostItem
import com.example.philosophyblog.utils.BaseApiResponse
import com.example.philosophyblog.utils.ScreenState
import okhttp3.MultipartBody
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : BaseApiResponse() {

    suspend fun getAllPosts(): ScreenState<List<PostItem>> {
        return safeApiCall {
            remoteDataSource.getAllPosts()
        }
    }

    suspend fun addPost(
        cover: MultipartBody.Part,
        title: MultipartBody.Part,
        description: MultipartBody.Part,
        text: MultipartBody.Part,
        tags: MultipartBody.Part,
        user: MultipartBody.Part,
    ): ScreenState<PostItem> {
        return safeApiCall {
            remoteDataSource.addPost(
                cover = cover,
                title = title,
                description = description,
                text = text,
                tags = tags,
                user = user
            )
        }
    }

    suspend fun updatePost(
        url: String,
        cover: MultipartBody.Part,
        title: MultipartBody.Part,
        description: MultipartBody.Part,
        text: MultipartBody.Part,
        tags: MultipartBody.Part,
        user: MultipartBody.Part,
    ): ScreenState<PostItem> {
        return safeApiCall {
            remoteDataSource.updatePost(
                url = url,
                cover = cover,
                title = title,
                description = description,
                text = text,
                tags = tags,
                user = user
            )
        }
    }

}