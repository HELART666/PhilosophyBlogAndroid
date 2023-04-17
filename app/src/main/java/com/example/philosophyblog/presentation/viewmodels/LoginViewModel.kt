package com.example.philosophyblog.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.philosophyblog.data.api.model.auth.AuthRequest
import com.example.philosophyblog.data.api.model.auth.AuthResponse
import com.example.philosophyblog.domain.usecases.*
import com.example.philosophyblog.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sendAuthRequestUseCase: SendAuthRequestUseCase,
    private val saveUserLoginUseCase: SaveUserLoginUseCase,
    private val saveUserPasswordUseCase: SaveUserPasswordUseCase,
    private val saveUserEmailUseCase: SaveUserEmailUseCase,
    private val getAccessTokenToSharedPreferencesUseCase: GetAccessTokenToSharedPreferencesUseCase,
    private val getRefreshTokenToSharedPreferencesUseCase: GetRefreshTokenToSharedPreferencesUseCase,
    private val saveAccessTokenToSharedPreferencesUseCase: SaveAccessTokenToSharedPreferencesUseCase,
    private val saveRefreshTokenToSharedPreferencesUseCase: SaveRefreshTokenToSharedPreferencesUseCase,
    private val saveUserIdUseCase: SaveUserIdUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
) : ViewModel() {
    private val authResponse = MutableLiveData<ScreenState<AuthResponse>>()
    val authResponseLiveData: LiveData<ScreenState<AuthResponse>> = authResponse
    private val accessToken = MutableLiveData<String?>()
    val accessTokenLiveData: LiveData<String?> = accessToken
    private val refreshToken = MutableLiveData<String?>()
    val refreshTokenLiveData: LiveData<String?> = refreshToken

    init {
        accessToken.value = getAccessTokenToSharedPreferencesUseCase.execute()
        refreshToken.value = getRefreshTokenToSharedPreferencesUseCase.execute()
    }


    fun sendAuthRequest(
        login: String,
        password: String,
    ) {
        viewModelScope.launch {
            authResponse.value = ScreenState.Loading()
            sendAuthRequestUseCase.execute(
                AuthRequest(
                    login = login,
                    password = password
                )
            ).let { state ->
                if (state is ScreenState.Success) {
                    state.data?.login?.let { login ->
                        saveUserLoginUseCase.execute(
                            login = login
                        )
                    }
                    saveUserPasswordUseCase.execute(
                        password = password
                    )
                    state.data?.userId?.let { userId ->
                        saveUserIdUseCase.execute(
                            userId = userId
                        )
                    }
                    state.data?.login?.let { email ->
                        saveUserEmailUseCase.execute(
                            email = email
                        )
                    }
                    state.data?.accessToken?.let { accessToken ->
                        saveAccessTokenToSharedPreferencesUseCase.execute(
                            accessToken = accessToken
                        )
                    }
                    state.data?.refreshToken?.let { refreshToken ->
                        saveRefreshTokenToSharedPreferencesUseCase.execute(
                            refreshToken = refreshToken
                        )
                    }
                }
                authResponse.value = state
            }
        }
    }
}