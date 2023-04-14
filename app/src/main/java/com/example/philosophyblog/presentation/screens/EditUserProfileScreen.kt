package com.example.philosophyblog.presentation.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.philosophyblog.R
import com.example.philosophyblog.data.api.model.user.Bio
import com.example.philosophyblog.data.api.model.user.NewUserFormData
import com.example.philosophyblog.data.api.model.user.User
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme
import com.example.philosophyblog.presentation.viewmodels.UserProfileViewModel
import com.example.philosophyblog.utils.SelectedItemState
import com.example.philosophyblog.utils.SliderState
import com.example.philosophyblog.utils.TextFieldState

// @Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun EditUserProfileScreen(
    userProfileViewModel: UserProfileViewModel,
    onBackClick: () -> Unit
) {
    val userInfoState = userProfileViewModel.userInfoStateLiveData.observeAsState()
    val ageState = remember { TextFieldState() }
    ageState.text = userInfoState.value?.data?.bio?.age ?: ""
    val locationState = remember { TextFieldState() }
    locationState.text = userInfoState.value?.data?.bio?.location ?: ""
    val bioState = remember { TextFieldState() }
    bioState.text = userInfoState.value?.data?.bio?.bio ?: ""
    val quoteState = remember { TextFieldState() }
    quoteState.text = userInfoState.value?.data?.bio?.quote ?: ""
    val philosophyDirectionState = remember { SelectedItemState() }
    philosophyDirectionState.text = userInfoState.value?.data?.bio?.philosophyDirection ?: ""
    val sexState = remember { TextFieldState() }
    sexState.text = userInfoState.value?.data?.bio?.sex ?: ""

    // извините меня пожалуйста те, кто это читает
    // бэкендер не захотел делать автозаполнение списка
    // я понимаю, что это очень плохо...
    val personality = userInfoState.value?.data?.bio?.personality?.toMutableList()
    if (personality != null && personality.isEmpty()) {
        personality.add("5")
        personality.add("5")
        personality.add("5")
        personality.add("5")
    }


    val firstCoord = remember { SliderState() }
    firstCoord.value = personality?.get(0)?.toFloat() ?: 5F
    val secondCoord = remember { SliderState() }
    secondCoord.value = personality?.get(1)?.toFloat() ?: 5F
    val thirdCoord = remember { SliderState() }
    thirdCoord.value = personality?.get(2)?.toFloat() ?: 5F
    val fourthCoord = remember { SliderState() }
    fourthCoord.value = personality?.get(3)?.toFloat() ?: 5F
    val userLogin = userProfileViewModel.userLoginLiveData.value
    val bitmapState = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val login = userInfoState.value?.data?.login
    var avatar: String? = null
    if (userInfoState.value?.data?.avatarUrl != null && userInfoState.value?.data?.avatarUrl?.isNotBlank() == true) {
        avatar =
            "http://192.168.42.135:4444/${userInfoState.value?.data?.avatarUrl}"
    }

    PhilosophyBlogTheme {
        Scaffold(
            topBar = {
                NavBackToolbar(
                    stringResource(id = R.string.edit_profile),
                    onBackClick = {
                        onBackClick()
                    }
                )
            },
            modifier = Modifier
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
                        bottom = paddingValues.calculateBottomPadding()
                    )
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.small_padding)
                    )
                    .fillMaxHeight()
            ) {
                EditUserAvatarImage(
                    bitmap = bitmapState,
                    avatarUrl = avatar
                )
                UserBioHeader(text = "Возраст")
                EditUserBioDescription(
                    state = ageState
                )
                UserBioHeader(text = "Локация")
                EditUserBioDescription(
                    state = locationState
                )
                UserBioHeader(text = "Биография")
                EditUserBioDescription(
                    state = bioState
                )
                UserBioHeader(text = "Цитата")
                EditUserBioDescription(
                    state = quoteState
                )
                UserBioHeader(text = "Направление")
                UserProfileDirectionsDropDownMenu(
                    selectedItem = philosophyDirectionState
                )
                UserBioHeader(text = "Пол")
                EditUserBioDescription(
                    state = sexState
                )
                UserBioHeader(text = "Координаты")
                Spacer(modifier = Modifier.height(16.dp))
                UserPhilosophyDirection(
                    left = "Релативизм",
                    right = "Абсолютизм"
                )
                EditUserProfileSlider(
                    state = firstCoord
                )
                UserPhilosophyDirection(
                    left = "Идеализм",
                    right = "Материализм"
                )
                EditUserProfileSlider(
                    state = secondCoord
                )
                UserPhilosophyDirection(
                    left = "Эскапизм",
                    right = "Реализм"
                )
                EditUserProfileSlider(
                    state = thirdCoord
                )
                UserPhilosophyDirection(
                    left = "Диалектика",
                    right = "Метафизика"
                )
                EditUserProfileSlider(
                    state = fourthCoord
                )

                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.primary)
                    ),
                    shape = CircleShape,
                    onClick = {
                        bitmapState.value?.let { bitmap ->
                            userProfileViewModel.updateUserInfo(
                                url = "http://192.168.42.135:4444/api/users/$userLogin",
                                avatar = bitmap,
                                formUserData = NewUserFormData(
                                    oldLogin = userLogin!!,
                                    user = User(
                                        userLogin,
                                        bio = Bio(
                                            age = ageState.text,
                                            bio = bioState.text,
                                            location = locationState.text,
                                            philosophyDirection = philosophyDirectionState.text,
                                            quote = quoteState.text,
                                            personality = listOf(
                                                firstCoord.value.toString(),
                                                secondCoord.value.toString(),
                                                thirdCoord.value.toString(),
                                                fourthCoord.value.toString(),
                                            ),
                                            sex = sexState.text,
                                        )
                                    )
                                )
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 72.dp
                        ),
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
fun EditUserAvatarImage(
    bitmap: MutableState<Bitmap?>,
    avatarUrl: String?,
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
    val localContext = LocalContext.current
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
                ) // я пишу это уже 14ый час, поэтому и тут я вынужден извиниться за такие костыли
            } ?: if (avatarUrl == null) {
                Image(
                    painter = painterResource(id = R.drawable.base_profile_avatar),
                    contentDescription = "base profile avatar",
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape),
                )
            } else {
                CoilImage(
                    imageUrl = avatarUrl,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape),
                    size = 128
                )
            }
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
fun EditUserBioDescription(
    state: TextFieldState,
) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = colorResource(id = R.color.primary),
        backgroundColor = colorResource(id = R.color.primary).copy(alpha = 0.4f)
    )
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        OutlinedTextField(
            value = state.text,
            textStyle = MaterialTheme.typography.body1,
            onValueChange = {
                state.text = it
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

@Composable
fun EditUserProfileSlider(
    state: SliderState,
) {
    Slider(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        steps = 10,
        valueRange = 0F..10F
    )
}

@Composable
fun UserProfileDirectionsDropDownMenu(
    selectedItem: SelectedItemState,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val directions = listOf(
        "Эмпиризм",
        "Рационализм",
        "Идеализм",
        "Позитивизм",
        "Стоицизм",
        "Структурализм",
        "Феноменология",
        "Материализм",
        "Экзистенциализм",
        "Скептицизм",
        "Цинизм",
        "Романтичность",
        "Догматизм",
        "Критика",
        "Контрактуализм",
        "Утилитаризм",
        "Коммунизм",
        "Социализм",
        "Либерализм",
        "Либертарианство"
    )
    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    OutlinedTextField(
        value = selectedItem.text,
        onValueChange = {
            selectedItem.text = it
        },
        trailingIcon = {
            Icon(
                icon, "dropDownMenuIcon", Modifier.clickable { expanded = !expanded }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }) {
        directions.forEach { direction ->
            DropdownMenuItem(
                onClick = {
                    selectedItem.text = direction
                    expanded = false
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = direction)
            }
        }
    }
}

