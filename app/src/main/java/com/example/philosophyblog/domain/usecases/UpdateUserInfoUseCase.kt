package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.api.model.user.NewUserData
import com.example.philosophyblog.data.repository.UserRepository
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(
        url: String,
        body: NewUserData
    ) = userRepository.updateUserInfo(
        url = url,
        body = body
    )


}