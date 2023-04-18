package com.example.philosophyblog.presentation.navigation.destination

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.philosophyblog.presentation.screens.EditPostScreen
import com.example.philosophyblog.presentation.viewmodels.PostsViewModel
import com.example.philosophyblog.presentation.viewmodels.UserProfileViewModel

fun NavGraphBuilder.editPost(
    navController: NavController,
    onUpdatePostClick: () -> Unit,
    onBackClick: () -> Unit
) {
    composable(
        route = "editPost/{postId}",
        arguments = listOf(
            navArgument("postId") {
                type = NavType.StringType
                nullable = false
            }
        )) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("posts")
        }
        val arguments = requireNotNull(backStackEntry.arguments)
        val postIndex = arguments.getString("postId")
        val userProfileEntry = remember(backStackEntry) {
            navController.getBackStackEntry("userProfile")
        }
        val parentViewModel = hiltViewModel<PostsViewModel>(parentEntry)
        val userProfileViewModel = hiltViewModel<UserProfileViewModel>(userProfileEntry)
        EditPostScreen(
            postViewModel = parentViewModel,
            userProfileViewModel = userProfileViewModel,
            onUpdatePostClick = onUpdatePostClick,
            onBackClick = onBackClick,
            postId = postIndex ?: "")
    }
}

fun NavController.navigateToEditPostScreen(
    postId: String
) {
    navigate("editPost/$postId")
}