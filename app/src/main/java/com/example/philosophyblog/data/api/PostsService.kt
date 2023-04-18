package com.example.philosophyblog.data.api


import com.example.philosophyblog.data.api.model.posts.PostItem
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface PostsService {

    @GET("api/posts")
    suspend fun getAllPosts() : Response<List<PostItem>>

    @Multipart
    @POST("api/posts")
    suspend fun addPost(
        @Part cover: MultipartBody.Part,
        @Part title: MultipartBody.Part,
        @Part description: MultipartBody.Part,
        @Part text: MultipartBody.Part,
        @Part tags: MultipartBody.Part,
        @Part user: MultipartBody.Part,
    ) : Response<PostItem>

    @Multipart
    @PATCH
    suspend fun updatePost(
        @Url url: String,
        @Part cover: MultipartBody.Part,
        @Part title: MultipartBody.Part,
        @Part description: MultipartBody.Part,
        @Part text: MultipartBody.Part,
        @Part tags: MultipartBody.Part,
        @Part user: MultipartBody.Part,
    ) : Response<PostItem>

}