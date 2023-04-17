package com.example.philosophyblog.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.philosophyblog.R
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme
import com.example.philosophyblog.presentation.viewmodels.UserListViewModel
import com.example.philosophyblog.utils.DateConverter

@Composable
fun AllUsersScreen(
    viewModel: UserListViewModel = hiltViewModel(),
) {
    val BASE_URL = "http://192.168.42.135:4444/"
    val dateConverter = DateConverter()
    val usersData = viewModel.usersLiveData.observeAsState()
    val usersList = usersData.value?.data
    PhilosophyBlogTheme() {
        Scaffold(
            topBar = {
                AppToolbar()
            }
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = it,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.small_padding)
                    ),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                usersList?.size?.let { iter ->
                    items(iter) { index ->
                        UserCard(
                            avatarUrl = "${BASE_URL}uploads/users/avatars/${usersList[index].login}-avatar.jpeg",
                            login = usersList[index].login,
                            createdAt = "В приложении с ${dateConverter.convertMongoDate(usersList[index].createdAt)}" ?: "Date"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserCard(
    avatarUrl: String?,
    login: String,
    createdAt: String,
) {
    Column(
        modifier = Modifier
            .clip(
                RoundedCornerShape(8.dp)
            )
            .background(
                color = colorResource(id = R.color.primary)
            )
            .padding(
                vertical = dimensionResource(id = R.dimen.smallest_padding),
                horizontal = dimensionResource(id = R.dimen.smaller_padding)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
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
                    .height(64.dp)
                    .width(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = login,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.smaller_padding))
        )
        Text(
            text = createdAt,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.smaller_padding))
        )
    }


}