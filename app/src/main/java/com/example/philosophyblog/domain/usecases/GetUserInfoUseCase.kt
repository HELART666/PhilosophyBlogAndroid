package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.repository.PostsRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
) {
    suspend fun getUserInfo(url: String) = postsRepository.getUserInfo(url = url)
}