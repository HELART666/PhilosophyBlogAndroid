package com.example.philosophyblog.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.philosophyblog.data.api.model.user.ShortUserResponse
import com.example.philosophyblog.domain.usecases.GetAllUsersUseCase
import com.example.philosophyblog.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
) : ViewModel() {
    private val users = MutableLiveData<ScreenState<List<ShortUserResponse>>>()
    val usersLiveData: LiveData<ScreenState<List<ShortUserResponse>>> = users

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                getAllUsersUseCase.execute().let { state ->
                    withContext(Dispatchers.Main) {
                        users.value = state
                    }
                }
            }
        }
    }
}