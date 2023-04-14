package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.repository.UserRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun execute() = userRepository.getAllUsers()
}