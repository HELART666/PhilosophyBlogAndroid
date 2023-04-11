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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.philosophyblog.R
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun EditUserProfileScreen() {
    PhilosophyBlogTheme {
        Scaffold(
            topBar = {
                NavBackToolbar(stringResource(id = R.string.edit_profile))
            },
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.small_padding)
                )
                .background(
                    color = colorResource(
                        id = R.color.white_background
                    ),
                )
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .background(
                        color = colorResource(
                            id = R.color.white_background
                        ),
                    )
                    .padding(
                        paddingValues
                    )
                    .fillMaxHeight()
            ) {
                EditUserAvatarImage()
                UserBioHeader(text = "Возраст")
                EditUserBioDescription()
                UserBioHeader(text = "Локация")
                EditUserBioDescription()
                UserBioHeader(text = "Биография")
                EditUserBioDescription()
                UserBioHeader(text = "Цитата")
                EditUserBioDescription()
                UserBioHeader(text = "Направление")
                EditUserBioDescription()
                UserBioHeader(text = "Координаты")
                EditUserBioDescription()
            }
        }


    }
}

@Composable
fun EditUserAvatarImage() {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
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
                contentDescription = "base profile avatar image",
                modifier = Modifier
                    .height(128.dp)
                    .width(128.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
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

@Composable
fun EditUserBioDescription() {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = colorResource(id = R.color.primary),
        backgroundColor = colorResource(id = R.color.primary).copy(alpha = 0.4f)
    )

    var text by remember { mutableStateOf(TextFieldValue("")) }
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        OutlinedTextField(
            value = text,
            textStyle = MaterialTheme.typography.body1,
            onValueChange = {
                text = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.smallest_padding)
                )
                .fillMaxWidth()
                .defaultMinSize(
                    minHeight = 1.dp
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.primary),
                unfocusedIndicatorColor = colorResource(id = R.color.primary),
                cursorColor = colorResource(R.color.primary)
            ),
            shape = RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 8.dp,
                bottomStart = 8.dp,
                bottomEnd = 8.dp
            )
        )
    }
}