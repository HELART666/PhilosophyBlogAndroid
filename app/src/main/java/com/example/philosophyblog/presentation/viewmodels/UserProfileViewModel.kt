package com.example.philosophyblog.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.philosophyblog.domain.usecases.GetUserLoginUseCase
import com.example.philosophyblog.domain.usecases.GetUserEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserLoginUseCase: GetUserLoginUseCase,
    private val getUserEmailUseCase: GetUserEmailUseCase,
): ViewModel()  {
    private val userLogin = MutableLiveData<String?>()
    val userLoginLiveData: LiveData<String?> = userLogin
    private val userEmail = MutableLiveData<String?>()
    val userEmailLiveData: LiveData<String?> = userEmail

    init {
        userLogin.value = getUserLoginUseCase.execute()
        userEmail.value = getUserEmailUseCase.execute()
    }

    fun getUserLogin() {
        userLogin.value = getUserLoginUseCase.execute()
    }

    fun getUserEmail() {
        userEmail.value = getUserEmailUseCase.execute()
    }


}