package com.example.philosophyblog.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.philosophyblog.data.api.model.posts.Posts
import com.example.philosophyblog.domain.usecases.GetAllPostsUseCase
import com.example.philosophyblog.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
) : ViewModel() {
    private val postsState = MutableLiveData<ScreenState<Posts>>()
    val postsLiveData: LiveData<ScreenState<Posts>> = postsState

    init {
        getAllPosts()
    }

    private fun getAllPosts() {
        viewModelScope.launch {
            getAllPostsUseCase.execute().let { state ->
                postsState.value = state
            }
        }
    }


}