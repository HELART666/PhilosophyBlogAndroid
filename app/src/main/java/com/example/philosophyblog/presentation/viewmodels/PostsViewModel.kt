package com.example.philosophyblog.presentation.viewmodels

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.philosophyblog.data.api.model.posts.PostItem
import com.example.philosophyblog.data.api.model.user.User
import com.example.philosophyblog.domain.usecases.AddPostUseCase
import com.example.philosophyblog.domain.usecases.GetAllPostsUseCase
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
class PostsViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val addPostUseCase: AddPostUseCase,
) : ViewModel() {
    private val postsState = MutableLiveData<ScreenState<List<PostItem>>>()
    val postsLiveData: LiveData<ScreenState<List<PostItem>>> = postsState
    private val sendState = MutableLiveData<ScreenState<PostItem>>()
    val sendData: LiveData<ScreenState<PostItem>> = sendState

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

    fun addPost(
        cover: Bitmap,
        title: String,
        description: String,
        text: String,
        tags: List<String>? = null,
        user: String,
    ) {
        viewModelScope.launch {
            val stream = ByteArrayOutputStream()
            cover.compress(Bitmap.CompressFormat.JPEG, 80, stream)
            val byteArray = stream.toByteArray()
            val coverBody = MultipartBody.Part.createFormData(
                "cover", "photo",
                byteArray.toRequestBody("image/jpg".toMediaTypeOrNull(), 0, byteArray.size)
            )
            val titleBody = MultipartBody.Part
                .createFormData(
                    "title",
                    title
                )
            val descriptionBody = MultipartBody.Part
                .createFormData(
                    "description",
                    description
                )
            val textBody = MultipartBody.Part
                .createFormData(
                    "text",
                    text
                )
            val tagsBody = MultipartBody.Part
                .createFormData(
                    "tags",
                    Gson().toJson(tags)
                )
            val userBody = MultipartBody.Part
                .createFormData(
                    "user",
                    user
                )

            addPostUseCase.execute(
                cover = coverBody,
                title = titleBody,
                description = descriptionBody,
                text = textBody,
                tags = tagsBody,
                user = userBody
            ).let { state ->
                sendState.value = state
                Log.d("MYLOGAAA", "$state")
                getAllPosts()
            }
        }
    }


}