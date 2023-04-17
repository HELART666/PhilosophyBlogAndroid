package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.sharedprefs.AuthSharedPreferences
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(
    private val authSharedPreferences: AuthSharedPreferences
) {
    fun execute(userId: String) {
        authSharedPreferences.userId = userId
    }
}