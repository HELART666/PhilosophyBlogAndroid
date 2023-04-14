package com.example.philosophyblog.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.philosophyblog.R
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme
import com.example.philosophyblog.presentation.viewmodels.UserProfileViewModel
import com.example.philosophyblog.utils.ScreenState

//@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserProfileScreen(
    userProfileViewModel: UserProfileViewModel = hiltViewModel(),
    onEditUserProfileButton: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    val logoutClickState = userProfileViewModel.isDialogShown.observeAsState()
    val email = userProfileViewModel.userEmailLiveData.observeAsState()
    val userInfo = userProfileViewModel.userInfoStateLiveData.observeAsState()
    var avatar: String? = null
    if (userInfo.value?.data?.avatarUrl != null && userInfo.value?.data?.avatarUrl?.isNotBlank() == true) {
        avatar =
            "http://192.168.42.135:4444/${userInfo.value?.data?.avatarUrl}"
    }

    // извините меня пожалуйста те, кто это читает
    // бэкендер не захотел делать автозаполнение списка
    // я понимаю, что это очень плохо...
    val personality = userInfo.value?.data?.bio?.personality?.toMutableList()
    if (personality != null && personality.isEmpty()) {
        personality.add("5")
        personality.add("5")
        personality.add("5")
        personality.add("5")
    }


    PhilosophyBlogTheme {
        Scaffold(
            topBar = {
                UserProfileToolbar(
                    viewModel = userProfileViewModel
                )
            },
            modifier = Modifier
                .background(
                    color = colorResource(
                        id = R.color.white_background
                    ),
                )
        ) { paddingValues ->
            if (userInfo.value !is ScreenState.Success) {
                LoadingScreen()
            } else {
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
                            bottom = 72.dp
                        )
                        .fillMaxHeight()
                ) {
                    if (logoutClickState.value == true) {
                        SimpleAlertDialog(
                            viewModel = userProfileViewModel,
                            onLogoutClick = onLogoutClick
                        )
                    }
                    UserProfileCard(
                        login = userInfo.value?.data?.login.toString(),
                        email = email.value.toString(),
                        onEditUserProfileButton = onEditUserProfileButton,
                        avatarUrl = avatar
                    )
                    UserInfo(
                        age = userInfo.value?.data?.bio?.age ?: "Не указано",
                        location = userInfo.value?.data?.bio?.location ?: "Не указано",
                        bio = userInfo.value?.data?.bio?.bio ?: "Не указано",
                        quote = userInfo.value?.data?.bio?.quote ?: "Не указано",
                        philosophyDirection = userInfo.value?.data?.bio?.philosophyDirection
                            ?: "Не указано",
                        sex = userInfo.value?.data?.bio?.sex ?: "Не указано",
                        firstCoord = personality?.get(0)?.toFloat() ?: 5F,
                        secondCoord = personality?.get(1)?.toFloat() ?: 5F,
                        thirdCoord = personality?.get(2)?.toFloat() ?: 5F,
                        fourthCoord = personality?.get(3)?.toFloat() ?: 5F,
                        goals = userInfo.value?.data?.bio?.goals ?: listOf("Не указаны"),
                        qualities = userInfo.value?.data?.bio?.qualities ?: listOf("Не указаны")
                    )
                }
            }
        }
    }
}

@Composable
fun UserProfileToolbar(
    viewModel: UserProfileViewModel,
) {
    TopAppBar(
        contentPadding = WindowInsets.systemBars
            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
            .asPaddingValues(),
        backgroundColor = colorResource(id = R.color.white_background),
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "PBLogo"
                    )
                }
                Text(
                    text = stringResource(id = R.string.philosophy),
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.small_padding)
                        ),
                    color = colorResource(id = R.color.primary)
                )
                Text(
                    text = stringResource(id = R.string.blog),
                    color = colorResource(id = R.color.primary_second)
                )
            }
            IconButton(
                onClick = {
                    viewModel.isDialogShow(true)
                },
                modifier = Modifier
                    .padding(
                        end = dimensionResource(id = R.dimen.small_padding)
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = "logout",
                    tint = colorResource(id = R.color.primary),
                )
            }
        }
    }
}

@Composable
fun UserProfileCard(
    avatarUrl: String?,
    login: String,
    email: String,
    editVisibility: Boolean = true,
    onEditUserProfileButton: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .padding(
                horizontal = dimensionResource(id = R.dimen.small_padding),
                vertical = dimensionResource(id = R.dimen.medium_padding)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (avatarUrl != null) {
            CoilImage(
                imageUrl = avatarUrl,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                size = 64
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.base_profile_avatar),
                contentDescription = "base profile avatar image",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = dimensionResource(id = R.dimen.small_padding)
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = login,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = email,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (editVisibility) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.white_background)
                ),
                shape = CircleShape,
                onClick = { onEditUserProfileButton() },
                border = BorderStroke(
                    2.dp, color = colorResource(id = R.color.primary),
                ),
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        vertical = dimensionResource(id = R.dimen.smallest_padding)
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "edit button icon",
                    modifier = Modifier
                        .padding(
                            end = dimensionResource(id = R.dimen.smallest_padding)
                        )
                        .size(
                            14.dp
                        ),
                    tint = colorResource(id = R.color.primary)
                )
                Text(
                    text = stringResource(id = R.string.edit),
                    color = colorResource(id = R.color.primary)
                )
            }
        }
    }
}

@Composable
fun UserInfo(
    age: String,
    location: String,
    bio: String,
    quote: String,
    philosophyDirection: String,
    sex: String,
    firstCoord: Float,
    secondCoord: Float,
    thirdCoord: Float,
    fourthCoord: Float,
    goals: List<String>,
    qualities: List<String>,
) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.small_padding)
            )
    ) {
        UserBioHeader(text = "Возраст")
        UserBioDescription(text = age)
        UserBioHeader(text = "Пол")
        UserBioDescription(text = sex)
        UserBioHeader(text = "Локация")
        UserBioDescription(text = location)
        UserBioHeader(text = "Биография")
        UserBioDescription(text = bio)
        UserBioHeader(text = "Цитата")
        UserBioDescription(text = quote)
        UserBioHeader(text = "Направление")
        UserBioDescription(text = philosophyDirection)
        UserBioHeader(text = "Цели")
        UserChipsRow(items = goals)
        UserBioHeader(text = "Качества")
        UserChipsRow(items = qualities)
        UserBioHeader(text = "Координаты")
        Spacer(modifier = Modifier.height(16.dp))
        UserPhilosophyDirection(
            left = "Релативизм",
            right = "Абсолютизм"
        )
        UserProfileSlider(
            value = firstCoord
        )
        UserPhilosophyDirection(
            left = "Идеализм",
            right = "Материализм"
        )
        UserProfileSlider(
            value = secondCoord
        )
        UserPhilosophyDirection(
            left = "Эскапизм",
            right = "Реализм"
        )
        UserProfileSlider(
            value = thirdCoord
        )
        UserPhilosophyDirection(
            left = "Диалектика",
            right = "Метафизика"
        )
        UserProfileSlider(
            value = fourthCoord
        )
    }
}

@Composable
fun UserBioHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h4,
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.small_padding)
            )
    )
}

@Composable
fun UserPhilosophyDirection(
    left: String,
    right: String,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = left,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.smaller_padding)
                ),
            color = colorResource(id = R.color.disable_gray)
        )
        Text(
            text = right,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.smaller_padding)
                ),
            color = colorResource(id = R.color.disable_gray)
        )
    }
}

@Composable
fun UserBioDescription(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.smaller_padding)
            ),
        color = colorResource(id = R.color.disable_gray)
    )
}

@Composable
fun UserProfileSlider(value: Float) {
    Slider(
        value = value,
        onValueChange = {},
        steps = 10,
        valueRange = 0f..10f,
        colors = SliderDefaults.colors(
            activeTickColor = Color.Transparent,
            inactiveTickColor = Color.Transparent,
            disabledActiveTrackColor = colorResource(id = R.color.primary_light),
            disabledActiveTickColor = colorResource(id = R.color.primary_light),
            disabledThumbColor = colorResource(id = R.color.primary)
        ),
        enabled = false
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserChip(item: String) {
    Chip(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.smallest_padding)
            ),
        onClick = { /*TODO*/ }
    ) {
        Text(text = item)
    }
}

@Composable
fun UserChipsRow(items: List<String>) {
    LazyRow {
        items(items) { goal ->
            UserChip(item = goal)
        }
    }
}

@Composable
fun SimpleAlertDialog(
    viewModel: UserProfileViewModel,
    onLogoutClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { viewModel.isDialogShow(false) },
        confirmButton = {
            TextButton(onClick = {
                viewModel.logout()
                onLogoutClick()
            })
            {
                Text(
                    text = stringResource(id = R.string.confirm),
                    style = MaterialTheme.typography.subtitle1,
                    color = colorResource(id = R.color.primary),
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {})
            {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = MaterialTheme.typography.subtitle1,
                    color = colorResource(id = R.color.logout_color),
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.please_confirm),
                style = MaterialTheme.typography.h4,
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.logout_question),
                style = MaterialTheme.typography.subtitle2,
            )
        },
        modifier = Modifier
            .clip(
                RoundedCornerShape(8.dp)
            )
    )
}