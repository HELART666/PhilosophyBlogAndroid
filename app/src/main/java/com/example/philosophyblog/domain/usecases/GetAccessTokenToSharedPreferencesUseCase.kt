package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.sharedprefs.AuthSharedPreferences
import javax.inject.Inject

class GetAccessTokenToSharedPreferencesUseCase @Inject constructor(
    private val authSharedPreferences: AuthSharedPreferences
) {
    fun execute() = authSharedPreferences.accessToken
}