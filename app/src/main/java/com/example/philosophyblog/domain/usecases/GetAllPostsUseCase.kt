package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.repository.PostRepository
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun execute() = postRepository.getAllPosts()
}