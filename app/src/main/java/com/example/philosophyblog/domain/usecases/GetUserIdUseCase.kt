package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.sharedprefs.AuthSharedPreferences
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val authSharedPreferences: AuthSharedPreferences
) {
    fun execute() = authSharedPreferences.userId
}