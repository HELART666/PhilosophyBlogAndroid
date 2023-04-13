package com.example.philosophyblog.presentation.navigation.destination

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.AddPostScreen
import com.example.philosophyblog.presentation.viewmodels.PostsViewModel

fun NavGraphBuilder.addPost(
    navController: NavController,
    onAddPostClick: () -> Unit,
) {
    composable("addPost") { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("posts")
        }
        val parentViewModel = hiltViewModel<PostsViewModel>(parentEntry)
        AddPostScreen(
            viewModel = parentViewModel,
            onAddPostClick = onAddPostClick
        )
    }
}

fun NavController.navigateToAddPostScreen() {
    navigate("addPost")
}