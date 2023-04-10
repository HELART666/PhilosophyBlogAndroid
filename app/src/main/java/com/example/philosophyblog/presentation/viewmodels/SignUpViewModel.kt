package com.example.philosophyblog.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.philosophyblog.data.api.model.signUp.SignUpRequest
import com.example.philosophyblog.data.api.model.signUp.SignUpResponse
import com.example.philosophyblog.domain.usecases.SendSignUpRequestUseCase
import com.example.philosophyblog.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val sendSignUpRequestUseCase: SendSignUpRequestUseCase,
) : ViewModel() {

    private val signUpResponse = MutableLiveData<ScreenState<SignUpResponse>>()
    val signUpResponseLiveData: LiveData<ScreenState<SignUpResponse>> = signUpResponse

    fun sendSignUpRequest(
        login: String,
        email: String,
        password: String,
    ) {
        viewModelScope.launch {
            sendSignUpRequestUseCase.execute(
                body = SignUpRequest(
                    login = login,
                    email = email,
                    password = password
                )
            ).let { state ->
                signUpResponse.value = state
            }
        }
    }

}