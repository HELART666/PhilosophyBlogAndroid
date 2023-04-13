package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.repository.PostRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class AddPostUseCase @Inject constructor(
    private val postRepository: PostRepository,
) {
    suspend fun execute(
        cover: MultipartBody.Part,
        title: MultipartBody.Part,
        description: MultipartBody.Part,
        text: MultipartBody.Part,
        tags: MultipartBody.Part,
        user: MultipartBody.Part,
    ) = postRepository.addPost(
        cover = cover,
        title = title,
        description = description,
        text = text,
        tags = tags,
        user = user,
    )
}