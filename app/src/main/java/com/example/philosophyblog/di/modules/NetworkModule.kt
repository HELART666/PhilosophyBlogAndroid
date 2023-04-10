package com.example.philosophyblog.di.modules

import com.example.philosophyblog.data.api.AuthService
import com.example.philosophyblog.data.api.UserService
import com.example.philosophyblog.data.api.SignUpService
import com.example.philosophyblog.data.api.helpers.AuthAuthenticator
import com.example.philosophyblog.data.api.helpers.AuthInterceptor
import com.example.philosophyblog.data.sharedprefs.AuthSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://192.168.42.135:4444"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesAuthInterceptor(authSharedPreferences: AuthSharedPreferences) =
        AuthInterceptor(authSharedPreferences)

    @Singleton
    @Provides
    fun providesAuthAuthenticator(
        authSharedPreferences: AuthSharedPreferences,
        authService: dagger.Lazy<AuthService>,
    ) = AuthAuthenticator(authSharedPreferences, authService)


    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator,
    ) =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .authenticator(authAuthenticator)
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit) =
        retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideSignUpService(retrofit: Retrofit) =
        retrofit.create(SignUpService::class.java)

    @Singleton
    @Provides
    fun provideUserPostsService(retrofit: Retrofit) =
        retrofit.create(UserService::class.java)

}