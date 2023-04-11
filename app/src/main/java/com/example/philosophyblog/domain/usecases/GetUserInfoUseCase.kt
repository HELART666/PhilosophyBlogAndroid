package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.repository.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun getUserInfo(url: String) = userRepository.getUserInfo(url = url)
}