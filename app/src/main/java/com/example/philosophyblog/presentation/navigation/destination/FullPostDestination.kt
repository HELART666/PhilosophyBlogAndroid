package com.example.philosophyblog.presentation.navigation.destination

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.philosophyblog.presentation.screens.FullPostInfoScreen
import com.example.philosophyblog.presentation.viewmodels.PostsViewModel

fun NavGraphBuilder.fullPost(
    navController: NavController,
) {
    composable(
        route = "fullPost/{postIndex}",
        arguments = listOf(
            navArgument("postIndex") {
                type = NavType.IntType
                nullable = false
            }
        )
    ) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("posts")
        }
        val arguments = requireNotNull(backStackEntry.arguments)
        val postIndex = arguments.getInt("postIndex")
        val parentViewModel = hiltViewModel<PostsViewModel>(parentEntry)
        FullPostInfoScreen(
            postsViewModel = parentViewModel,
            postIndex = postIndex
        )
    }
}

fun NavController.navigateToFullPostScreen(
    postIndex: Int
) {
    navigate("fullPost/$postIndex")
}