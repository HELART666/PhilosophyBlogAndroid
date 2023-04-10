package com.example.philosophyblog.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.philosophyblog.data.api.model.user.UserInfoResponse
import com.example.philosophyblog.domain.usecases.GetUserLoginUseCase
import com.example.philosophyblog.domain.usecases.GetUserEmailUseCase
import com.example.philosophyblog.domain.usecases.GetUserInfoUseCase
import com.example.philosophyblog.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserLoginUseCase: GetUserLoginUseCase,
    private val getUserEmailUseCase: GetUserEmailUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
): ViewModel()  {
    private val userLogin = MutableLiveData<String?>()
    val userLoginLiveData: LiveData<String?> = userLogin
    private val userEmail = MutableLiveData<String?>()
    val userEmailLiveData: LiveData<String?> = userEmail
    private val userInfoState = MutableLiveData<ScreenState<UserInfoResponse>>()
    val userInfoStateLiveData: LiveData<ScreenState<UserInfoResponse>> = userInfoState

    init {
        getUserEmail()
        getUserLogin()
    }

    private fun getUserLogin() {
        userLogin.value = getUserLoginUseCase.execute()
    }

    private fun getUserEmail() {
        userEmail.value = getUserEmailUseCase.execute()
    }

    fun getUserInfo(url: String) {
        viewModelScope.launch {
            getUserInfoUseCase.getUserInfo(
                url = url
            ).let { state ->
                userInfoState.value = state
            }
        }

    }

}