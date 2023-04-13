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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.philosophyblog.R
import com.example.philosophyblog.data.api.model.user.Bio
import com.example.philosophyblog.data.api.model.user.User
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme
import com.example.philosophyblog.presentation.viewmodels.PostsViewModel
import com.example.philosophyblog.utils.TextFieldState

@Composable
fun AddPostScreen(
    viewModel: PostsViewModel,
    onAddPostClick: () -> Unit
) {
    val title = remember { TextFieldState() }
    val description = remember { TextFieldState() }
    val text = remember { TextFieldState() }
    val bitmapState = remember {
        mutableStateOf<Bitmap?>(null)
    }
    PhilosophyBlogTheme() {
        Scaffold(
            topBar = { UserProfileToolbar() }
        ) { paddingValues ->
            Column(
                modifier = Modifier
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
                        viewModel.addPost(
                            cover = bitmapState.value!!,
                            title = title.text,
                            description = description.text,
                            text = text.text,
                            user = "642c0d1f139ec3baf8274e14"
                        )
                        onAddPostClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.small_padding)
                        )
                ) {
                    Text(
                        text = "Отредактировать",
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


@Composable
fun EditPostAvatarImage(
    bitmap: MutableState<Bitmap?>,
) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri
    }


    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(
                    id = R.dimen.medium_padding
                )
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            bitmap.value?.let { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "base profile avatar image",
                    modifier = Modifier
                        .height(128.dp)
                        .width(128.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } ?: Image(
                painter = painterResource(id = R.drawable.base_profile_avatar),
                contentDescription = "base profile avatar",
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape),
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.primary_second)
                ),
                onClick = {
                    launcher.launch("image/*")
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "avatar edit icon",
                    tint = Color.White
                )
            }
        }
    }
}
