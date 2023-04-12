package com.example.philosophyblog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.philosophyblog.presentation.navigation.destination.*

@Composable
fun PhilosophyBlogNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "posts"
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
            }
        )
        editUserProfile(
            navController
        )
        signUp(
            navToProfile = {
                navController.navigateToUserProfile()
            }
        )
        posts()
    }
}