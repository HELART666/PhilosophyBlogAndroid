package com.example.philosophyblog.presentation.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.philosophyblog.data.api.model.user.NewUserFormData
import com.example.philosophyblog.data.api.model.user.UserInfoResponse
import com.example.philosophyblog.domain.usecases.*
import com.example.philosophyblog.utils.ScreenState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserLoginUseCase: GetUserLoginUseCase,
    private val getUserEmailUseCase: GetUserEmailUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val updateUserInfoUseCase: UpdateUserInfoUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    private val userLogin = MutableLiveData<String?>()
    val userLoginLiveData: LiveData<String?> = userLogin
    private val userEmail = MutableLiveData<String?>()
    val userEmailLiveData: LiveData<String?> = userEmail
    private val userInfoState = MutableLiveData<ScreenState<UserInfoResponse>>()
    val userInfoStateLiveData: LiveData<ScreenState<UserInfoResponse>> = userInfoState


    init {
        getUserEmail()
        getUserLogin()
        getUserInfo(
            url = "http://192.168.42.135:4444/api/users/${userLogin.value}"
        )
    }

    private fun getUserLogin() {
        userLogin.value = getUserLoginUseCase.execute()
    }

    private fun getUserEmail() {
        userEmail.value = getUserEmailUseCase.execute()
    }

    private fun getUserInfo(url: String) {
        viewModelScope.launch {
            getUserInfoUseCase.execute(
                url = url
            ).let { state ->
                userInfoState.value = state
            }
        }
    }

    fun updateUserInfo(
        url: String,
        avatar: Bitmap?,
        formUserData: NewUserFormData,
    ) {
        val stream = ByteArrayOutputStream()
        avatar?.compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val avatarBody = MultipartBody.Part.createFormData(
            "avatar", "photo",
            byteArray.toRequestBody("image/jpg".toMediaTypeOrNull(), 0, byteArray.size)
        )
        val formUserDataBody = MultipartBody.Part
            .createFormData(
                "user",
                Gson().toJson(formUserData)
            )

        viewModelScope.launch {
            updateUserInfoUseCase.execute(
                url = url,
                avatar = avatarBody,
                formUserData = formUserDataBody
            ).let { state ->
                userInfoState.value = state
                getUserInfo(
                    url = "http://192.168.42.135:4444/api/users/${userLogin.value}"
                )
            }
        }
    }

    fun logout() = logoutUseCase.execute()

}