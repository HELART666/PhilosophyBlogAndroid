package com.example.philosophyblog.presentation.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.UserProfileScreen

fun NavGraphBuilder.userProfile(
    onEditUserProfileButton: () -> Unit,
    onLogoutClick: () -> Unit
) {
    composable("userProfile") {
        UserProfileScreen(
            onEditUserProfileButton = {
                onEditUserProfileButton()
            },
            onLogoutClick = {
                onLogoutClick()
            }
        )
    }
}

fun NavController.navigateToUserProfile() {
    navigate("userProfile")
}