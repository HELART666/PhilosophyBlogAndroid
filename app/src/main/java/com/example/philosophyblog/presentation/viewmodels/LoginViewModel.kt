package com.example.philosophyblog.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.philosophyblog.data.api.model.AuthRequest
import com.example.philosophyblog.data.api.model.AuthResponse
import com.example.philosophyblog.domain.usecases.SendAuthRequestUseCase
import com.example.philosophyblog.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sendAuthRequestUseCase: SendAuthRequestUseCase,
) : ViewModel() {
    private val authResponse = MutableLiveData<ScreenState<AuthResponse>>()
    val authResponseLiveData: LiveData<ScreenState<AuthResponse>> = authResponse

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
                authResponse.value = state
            }
        }
    }

}