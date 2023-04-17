package com.example.philosophyblog.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.philosophyblog.R
import com.example.philosophyblog.data.api.model.posts.PostItem
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme
import com.example.philosophyblog.presentation.viewmodels.PostsViewModel
import com.example.philosophyblog.utils.Consts.BASE_URL
import com.example.philosophyblog.utils.ScreenState
import kotlin.math.max
import kotlin.math.min

val AppBarCollapsedHeight = 56.dp
val AppBarExpendedHeight = 400.dp


@Composable
fun FullPostInfoScreen(
    postsViewModel: PostsViewModel,
    postIndex: Int,
) {
    val scrollState = rememberLazyListState()
    val postsState = postsViewModel.postsLiveData.observeAsState()
    PhilosophyBlogTheme() {
        Box() {
            Content(
                scrollState = scrollState,
                postState = postsState,
                postIndex = postIndex
            )
            ParallaxToolbar(
                scrollState = scrollState,
                postIndex = postIndex,
                postState = postsState
            )
        }
    }
}

@Composable
fun ParallaxToolbar(
    scrollState: LazyListState,
    postIndex: Int,
    postState: State<ScreenState<List<PostItem>>?>,
) {
    val posts = postState.value?.data
    val currentPost = posts?.get(postIndex)

    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

    val maxOffset =
        with(LocalDensity.current) { imageHeight.roundToPx() }

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    TopAppBar(
        contentPadding = WindowInsets.systemBars
            .only(WindowInsetsSides.Horizontal)
            .asPaddingValues(),
        backgroundColor = White,
        modifier = Modifier
            .height(
                AppBarExpendedHeight
            )
            .fillMaxWidth()
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = 0.dp
    ) {
        Column {
            Box(
                Modifier
                    .height(imageHeight)
                    .graphicsLayer {
                        alpha = 1f - offsetProgress
                    }) {
                if (currentPost?.imgUrl != null) {
                    CoilImage(
                        imageUrl = "${BASE_URL}${currentPost.imgUrl}",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 12.dp,
                                    bottomEnd = 12.dp,
                                )
                            ),
                        size = 400
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.base_profile_avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 12.dp,
                                    bottomEnd = 12.dp,
                                )
                            )
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f, Transparent),
                                    Pair(1f, White)
                                )
                            )
                        )
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(AppBarExpendedHeight),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = currentPost?.title ?: "Тут должен быть ваш заголовок",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.small_padding)
                        )
                        .scale(1f - 0.25f * offsetProgress),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun Content(
    scrollState: LazyListState,
    postState: State<ScreenState<List<PostItem>>?>,
    postIndex: Int,
) {
    val posts = postState.value?.data
    val currentPost = posts?.get(postIndex)
    val currentAvatar =
        "${BASE_URL}${currentPost?.user?.avatarUrl}"

    LazyColumn(
        contentPadding = PaddingValues(top = AppBarExpendedHeight),
        state = scrollState,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(1) {
            UserProfileCard(
                avatarUrl = currentAvatar,
                login = currentPost?.user?.login ?: "",
                email = currentPost?.user?.login ?: "",
                editVisibility = false,
                onEditUserProfileButton = {}
            )
            Text(
                text = currentPost?.text ?: "Тут должен быть текст поста",
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.small_padding),
                        vertical = dimensionResource(id = R.dimen.small_padding)
                    ),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}