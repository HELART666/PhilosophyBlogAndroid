package com.example.philosophyblog.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.philosophyblog.R
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme
import com.example.philosophyblog.presentation.viewmodels.UserProfileViewModel

//@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun UserProfileScreen(
    userProfileViewModel: UserProfileViewModel = hiltViewModel()
) {

    val login = userProfileViewModel.userLoginLiveData.observeAsState()
    val email = userProfileViewModel.userEmailLiveData.observeAsState()

    PhilosophyBlogTheme {
        Column(
            modifier = Modifier
                .background(
                    color = colorResource(
                        id = R.color.white_background
                    ),
                )
                .fillMaxHeight()
        ) {
            UserProfileToolbar()
            UserProfileCard(
                login = login.value.toString(),
                email = email.value.toString()
            )
            UserInfo()
        }
    }
}

@Composable
fun UserProfileToolbar() {
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
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "PBLogo"
                    )
                }
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.small_padding)
                        )
                )
            }
        }
    }
}

@Composable
fun UserProfileCard(
    login: String,
    email: String
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
        Image(
            painter = painterResource(id = R.drawable.base_profile_avatar),
            contentDescription = "base profile avatar image",
            modifier = Modifier
                .height(64.dp)
                .width(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
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
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.white_background)
            ),
            shape = CircleShape,
            onClick = { /*TODO*/ },
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

@Composable
fun UserInfo() {
    Column(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.small_padding)
            )
    ) {
        UserBioHeader(text = "Возраст")
        UserBioDescription(text = "20")
        UserBioHeader(text = "Локация")
        UserBioDescription(text = "Ульяновск, Россия")
        UserBioHeader(text = "Биография")
        UserBioDescription(text = "Ровный парень реально, люблю философские рассуждения и всё, что с ними связано")
        UserBioHeader(text = "Цитата")
        UserBioDescription(text = "Кто не кто, тот никто")
        UserBioHeader(text = "Направление")
        UserBioDescription(text = "Правильное")
        UserBioHeader(text = "Координаты")
        UserBioDescription(text = "Туда сюда 13.37")
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
