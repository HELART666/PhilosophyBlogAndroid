package com.example.philosophyblog.data.api.helpers

import android.util.Log
import com.example.philosophyblog.data.sharedprefs.AuthSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authSharedPreferences: AuthSharedPreferences,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        authSharedPreferences.accessToken?.let { token ->
            request.addHeader(
                "Authorization", "Bearer $token"
            )
        }
        Log.d("AUTHSHAREDPREFERENCESSSSSSSSSS", "${authSharedPreferences.accessToken}")
        Log.d("AUTHSHAREDPREFERENCESSSSSSSSSS", "${authSharedPreferences.refreshToken}")
        val buildRequest = request.build()
        return chain.proceed(buildRequest)
    }
}