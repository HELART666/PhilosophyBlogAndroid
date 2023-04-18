package com.example.philosophyblog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.philosophyblog.presentation.navigation.destination.*

@Composable
fun PhilosophyBlogNavigation(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        login(
            onLoginButtonClick = {
                navController.navigateToUserProfile()
            },
            onSignUpButtonClick = {
                navController.navigateToSignUp()
            }
        )
        userProfile(
            onEditUserProfileButton = {
                navController.navigateToEditUserProfile()
            },
            onLogoutClick = {
                navController.navigateToLogin()
            }
        )
        editUserProfile(
            navController,
            onBackClick = {
                navController.popBackStack()
            }
        )
        signUp(
            navToProfile = {
                navController.navigateToUserProfile()
            }
        )
        posts(
            onPostClick = { index ->
                navController.navigateToFullPostScreen(
                    postIndex = index
                )
            },
            onPostAddClick = {
                navController.navigateToAddPostScreen()
            },
            onPostEditClick = { postId ->
                navController.navigateToEditPostScreen(
                    postId = postId
                )
            }
        )
        fullPost(
            navController
        )
        addPost(
            navController = navController,
            onAddPostClick = {
                navController.popBackStack()
            },
            onBackClick = {
                navController.popBackStack()
            }
        )
        allUsers()

        editPost(
            navController = navController,
            onUpdatePostClick = {
                navController.popBackStack()
            },
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}