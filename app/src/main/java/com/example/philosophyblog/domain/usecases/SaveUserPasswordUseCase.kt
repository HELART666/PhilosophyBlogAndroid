package com.example.philosophyblog.domain.usecases

import com.example.philosophyblog.data.sharedprefs.AuthSharedPreferences
import javax.inject.Inject

class SaveUserPasswordUseCase @Inject constructor(
    private val authSharedPreferences: AuthSharedPreferences
) {
    fun execute(password: String) {
        authSharedPreferences.password = password
    }
}