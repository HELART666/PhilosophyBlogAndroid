package com.example.philosophyblog.data.api.model

import com.example.philosophyblog.data.api.model.posts.Posts
import retrofit2.Response
import retrofit2.http.GET

interface PostsService {

    @GET("api/posts")
    suspend fun getAllPosts() : Response<Posts>

}