package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.sharedprefs.AuthSharedPreferences
import javax.inject.Inject

class SaveAccessTokenToSharedPreferencesUseCase @Inject constructor(
    private val authSharedPreferences: AuthSharedPreferences
)  {
    fun execute(accessToken: String) {
        authSharedPreferences.accessToken = accessToken
    }
}