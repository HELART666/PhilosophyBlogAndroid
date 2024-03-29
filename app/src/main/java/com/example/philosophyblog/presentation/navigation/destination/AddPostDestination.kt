package com.example.philosophyblog.presentation.navigation.destination

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.AddPostScreen
import com.example.philosophyblog.presentation.viewmodels.PostsViewModel
import com.example.philosophyblog.presentation.viewmodels.UserProfileViewModel

fun NavGraphBuilder.addPost(
    navController: NavController,
    onAddPostClick: () -> Unit,
    onBackClick: () -> Unit
) {
    composable("addPost") { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("posts")
        }
        val userProfileEntry = remember(backStackEntry) {
            navController.getBackStackEntry("userProfile")
        }
        val parentViewModel = hiltViewModel<PostsViewModel>(parentEntry)
        val userProfileViewModel = hiltViewModel<UserProfileViewModel>(userProfileEntry)
        AddPostScreen(
            userProfileViewModel = userProfileViewModel,
            postViewModel = parentViewModel,
            onAddPostClick = onAddPostClick,
            onBackClick = onBackClick
        )
    }
}

fun NavController.navigateToAddPostScreen() {
    navigate("addPost")
}