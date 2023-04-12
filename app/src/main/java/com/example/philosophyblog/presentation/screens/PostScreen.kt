package com.example.philosophyblog.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.philosophyblog.R
import com.example.philosophyblog.presentation.viewmodels.PostsViewModel

@Composable
fun PostScreen(
    postsViewModel: PostsViewModel = hiltViewModel(),
) {
    val header = postsViewModel.postsLiveData.observeAsState()



    Scaffold(
        topBar = { NavBackToolbar(stringResource(id = R.string.app_name)) }
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = it.calculateBottomPadding()
                )
        ) {
            PostCard(
                header = headerT,
                date = date
            )

        }
    }
}


@Composable
fun PostCard(
    header: String,
    date: String,
) {
    Row() {
        Image(
            painter = painterResource(id = R.drawable.base_profile_avatar),
            contentDescription = "post image",
            modifier = Modifier
                .size(148.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Column() {
            Text(
                text = header,
                maxLines = 3,
                style = MaterialTheme.typography.h3
            )
            Text(
                text = date,
                maxLines = 1,
                style = MaterialTheme.typography.body2
            )
        }
    }
}