package com.example.philosophyblog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.philosophyblog.presentation.navigation.destination.login
import com.example.philosophyblog.presentation.navigation.destination.navigateToUserProfile
import com.example.philosophyblog.presentation.navigation.destination.userProfile

@Composable
fun PhilosophyBlogNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        login(
            onLoginButtonClick = {
                navController.navigateToUserProfile()
            }
        )
        userProfile()
    }
}