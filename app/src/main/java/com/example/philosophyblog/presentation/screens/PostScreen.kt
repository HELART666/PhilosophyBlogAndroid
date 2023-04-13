package com.example.philosophyblog.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.philosophyblog.R
import com.example.philosophyblog.presentation.viewmodels.PostsViewModel

@Composable
fun PostScreen(
    postsViewModel: PostsViewModel = hiltViewModel(),
    onPostClick: (Int) -> Unit,
    onPostAddClick: () -> Unit,
) {
    val postsState = postsViewModel.postsLiveData.observeAsState()
    val posts = postsState.value?.data


    Scaffold(
        topBar = { UserProfileToolbar() },
        floatingActionButton = {
            FloatingActionButtonAddPost(
                onPostAddClick = onPostAddClick
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(
                    bottom = it.calculateBottomPadding()
                )
                .padding(
                    bottom = 72.dp
                )
                .background(colorResource(id = R.color.white_background))
        ) {
            if (posts != null) {
                itemsIndexed(items = posts.toList()) { index, post ->
                    PostCard(
                        imageUrl = "http://192.168.42.135:4444/${post.imgUrl}",
                        header = post.title,
                        date = post.createdAt,
                        postIndex = index,
                        onPostClick = { onPostClick(index) }
                    )
                }
            }
        }
    }
}


@Composable
fun PostCard(
    imageUrl: String?,
    header: String,
    date: String,
    onPostClick: (Int) -> Unit,
    postIndex: Int,
) {
    Row(
        modifier = Modifier
            .padding(
                dimensionResource(id = R.dimen.small_padding),
            )
            .clickable {
                onPostClick(postIndex)
            }
    ) {
        if (imageUrl != null) {
            CoilImage(
                imageUrl = imageUrl,
                modifier = Modifier
                    .size(124.dp)
                    .clip(RoundedCornerShape(12.dp)),
                size = 128
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.base_profile_avatar),
                contentDescription = "post image",
                modifier = Modifier
                    .size(124.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.small_padding)
                )
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = header,
                maxLines = 3,
                style = MaterialTheme.typography.h4
            )
            Text(
                text = date,
                maxLines = 1,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun FloatingActionButtonAddPost(
    onPostAddClick: () -> Unit,
) {
    IconButton(
        modifier = Modifier
            .padding(
                bottom = 48.dp,
                end = 12.dp,
            )
            .size(64.dp)
            .clip(CircleShape)
            .background(colorResource(id = R.color.primary)),
        onClick = {
            onPostAddClick()
        },
    ) {
        Icon(
            imageVector = Icons.Sharp.Add,
            contentDescription = "",
            tint = Color.White
        )
    }
}