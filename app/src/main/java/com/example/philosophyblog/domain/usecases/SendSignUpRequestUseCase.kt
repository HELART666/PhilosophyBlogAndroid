package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.api.model.signUp.SignUpRequest
import com.example.philosophyblog.data.repository.SignUpRepository
import javax.inject.Inject

class SendSignUpRequestUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend fun execute(body: SignUpRequest) = signUpRepository.sendSignUpRequest(body = body)
}