package com.example.philosophyblog.domain.usecases


import com.example.philosophyblog.data.repository.UserRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(
        url: String,
        avatar: MultipartBody.Part,
        formUserData: MultipartBody.Part,
    ) = userRepository.updateUserInfo(
        url = url,
        avatar = avatar,
        formUserData = formUserData
    )


}