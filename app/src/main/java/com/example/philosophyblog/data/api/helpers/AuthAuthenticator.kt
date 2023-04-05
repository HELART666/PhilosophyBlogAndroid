package com.example.philosophyblog.data.api.helpers

import android.util.Log
import com.example.philosophyblog.data.api.AuthService
import com.example.philosophyblog.data.sharedprefs.AuthSharedPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val authSharedPreferences: AuthSharedPreferences,
    private val authService: dagger.Lazy<AuthService>
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshTokenResponse = runBlocking {
            authService.get().getNewTokens()
        }
        val sessionDataResponse = refreshTokenResponse.body()
        if (refreshTokenResponse.isSuccessful && sessionDataResponse != null) {
            Log.d("Authenticator", "shared ${sessionDataResponse.accessToken}")
            Log.d("Authenticator", "shared ${sessionDataResponse.refreshToken}")
            authSharedPreferences.accessToken = sessionDataResponse.accessToken
            authSharedPreferences.refreshToken = sessionDataResponse.refreshToken
            Log.d("Authenticator", "session ${sessionDataResponse.accessToken}")
            Log.d("Authenticator", "session ${sessionDataResponse.refreshToken}")
            // retry request with the new tokens
            return response.request
                .newBuilder()
                .header("authorization", "Bearer ${authSharedPreferences.accessToken}")
                .build()
        }
        return null
    }
}
