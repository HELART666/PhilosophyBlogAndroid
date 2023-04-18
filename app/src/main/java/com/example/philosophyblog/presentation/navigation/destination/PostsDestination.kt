package com.example.philosophyblog.presentation.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.PostScreen

fun NavGraphBuilder.posts(
    onPostClick: (Int) -> Unit,
    onPostAddClick: () -> Unit,
    onPostEditClick: (String) -> Unit,
) {
    composable("posts") {
        PostScreen(
            onPostClick = onPostClick,
            onPostAddClick = onPostAddClick,
            onPostEditClick = onPostEditClick
        )
    }
}

fun NavController.navigateToPosts() {
    navigate("posts")
}