package com.example.philosophyblog.presentation.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.philosophyblog.R
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme
import com.example.philosophyblog.presentation.viewmodels.PostsViewModel
import com.example.philosophyblog.presentation.viewmodels.UserProfileViewModel
import com.example.philosophyblog.utils.Consts.BASE_URL
import com.example.philosophyblog.utils.TextFieldState

@Composable
fun EditPostScreen(
    postViewModel: PostsViewModel,
    userProfileViewModel: UserProfileViewModel,
    onUpdatePostClick: () -> Unit,
    onBackClick: () -> Unit,
    postId: String,
) {
    val title = remember { TextFieldState() }
    val description = remember { TextFieldState() }
    val text = remember { TextFieldState() }
    val bitmapState = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val userId = userProfileViewModel.userIdLiveData.observeAsState()
    val userLogin = userProfileViewModel.userLoginLiveData.observeAsState()

    PhilosophyBlogTheme() {
        Scaffold(
            topBar = {
                NavBackToolbar(
                    title = "Редактирование публикации"
                ) {
                    onBackClick()
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(
                        bottom = paddingValues.calculateBottomPadding()
                    )
                    .fillMaxSize()
                    .background(colorResource(id = R.color.white_background))
                    .padding(
                        horizontal = 16.dp,
                    )
            ) {
                EditPostAvatarImage(
                    bitmap = bitmapState
                )
                Spacer(
                    modifier = Modifier
                        .height(24.dp)
                )
                UserBioDescription(text = "Заголовок")
                EditUserBioDescription(
                    state = title
                )
                UserBioDescription(text = "Описание")
                EditUserBioDescription(
                    state = description
                )
                UserBioDescription(text = "Содержание")
                EditUserBioDescription(
                    state = text
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.primary)
                    ),
                    shape = CircleShape,
                    onClick = {
                        postViewModel.updatePost(
                            url = "${BASE_URL}api/posts/${postId}",
                            cover = bitmapState.value!!,
                            title = title.text,
                            description = description.text,
                            text = text.text,
                            user = userId.value.toString()
                        )
                        onUpdatePostClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.small_padding),
                            bottom = 72.dp
                        )
                ) {
                    Text(
                        text = "Опубликовать",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(
                                vertical = 18.dp,
                                horizontal = 16.dp
                            ),
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
        }
    }
}