package com.example.philosophyblog.data.api

import com.example.philosophyblog.data.api.model.auth.AuthRequest
import com.example.philosophyblog.data.api.model.signUp.SignUpRequest
import okhttp3.MultipartBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val authService: AuthService,
    private val signUpService: SignUpService,
    private val userService: UserService,
    private val postsService: PostsService,
) {

    suspend fun sendAuthRequest(body: AuthRequest) = authService.sendAuthRequest(body = body)

    suspend fun sendSignUpRequest(body: SignUpRequest) =
        signUpService.sendSignUpRequest(body = body)

    suspend fun getUserInfo(url: String) = userService.getUserInfo(url = url)

    suspend fun getAllPosts() = postsService.getAllPosts()

    suspend fun addPost(
        cover: MultipartBody.Part,
        title: MultipartBody.Part,
        description: MultipartBody.Part,
        text: MultipartBody.Part,
        tags: MultipartBody.Part,
        user: MultipartBody.Part,
    ) = postsService.addPost(
        cover = cover,
        title = title,
        description = description,
        text = text,
        tags = tags,
        user = user
    )

    suspend fun updateUserInfo(
        url: String,
        avatar: MultipartBody.Part,
        formUserData: MultipartBody.Part,
    ) = userService.updateUserInfo(
        url = url,
        avatar = avatar,
        formUserData = formUserData
    )
}