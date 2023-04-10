package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.api.model.auth.AuthRequest
import com.example.philosophyblog.data.repository.AuthRepository
import javax.inject.Inject

class SendAuthRequestUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend fun execute(body: AuthRequest) = authRepository.sendAuthRequest(body = body)
}