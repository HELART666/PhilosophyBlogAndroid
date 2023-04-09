package com.example.philosophyblog.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.philosophyblog.data.api.model.AuthRequest
import com.example.philosophyblog.data.api.model.AuthResponse
import com.example.philosophyblog.domain.usecases.*
import com.example.philosophyblog.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sendAuthRequestUseCase: SendAuthRequestUseCase,
    private val saveUserLoginUseCase: SaveUserLoginUseCase,
    private val saveUserPasswordUseCase: SaveUserPasswordUseCase,
    private val getAccessTokenToSharedPreferencesUseCase: GetAccessTokenToSharedPreferencesUseCase,
    private val getRefreshTokenToSharedPreferencesUseCase: GetRefreshTokenToSharedPreferencesUseCase,
    private val saveAccessTokenToSharedPreferencesUseCase: SaveAccessTokenToSharedPreferencesUseCase,
    private val saveRefreshTokenToSharedPreferencesUseCase: SaveRefreshTokenToSharedPreferencesUseCase,
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
            sendAuthRequestUseCase.execute(
                AuthRequest(
                    login = login,
                    password = password
                )
            ).let { state ->
                if (state is ScreenState.Success) {
                    state.data?.login?.let { email ->
                        saveUserLoginUseCase.execute(
                            login = login
                        )
                    }
                    saveUserPasswordUseCase.execute(
                        password = password
                    )
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