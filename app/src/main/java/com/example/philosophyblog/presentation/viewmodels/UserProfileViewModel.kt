package com.example.philosophyblog.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.philosophyblog.domain.usecases.GetUserLoginUseCase
import com.example.philosophyblog.domain.usecases.GetUserPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserLoginUseCase: GetUserLoginUseCase,
    private val getUserPasswordUseCase: GetUserPasswordUseCase,
): ViewModel()  {
    private val userLogin = MutableLiveData<String?>()
    val userLoginLiveData: LiveData<String?> = userLogin
    private val userPassword = MutableLiveData<String?>()
    val userPasswordLiveData: LiveData<String?> = userPassword

    fun saveUserLogin() {
        userLogin.value = getUserLoginUseCase.execute()
    }

    fun saveUserPassword() {
        userPassword.value = getUserPasswordUseCase.execute()
    }


}