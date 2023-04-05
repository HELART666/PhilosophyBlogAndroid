package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.sharedprefs.AuthSharedPreferences
import javax.inject.Inject

class SaveRefreshTokenToSharedPreferencesUseCase @Inject constructor(
    private val authSharedPreferences: AuthSharedPreferences
) {
    fun execute(refreshToken: String) {
        authSharedPreferences.refreshToken = refreshToken
    }
}