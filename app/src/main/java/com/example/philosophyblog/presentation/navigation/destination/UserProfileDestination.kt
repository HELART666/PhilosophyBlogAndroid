package com.example.philosophyblog.presentation.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.UserProfileScreen

fun NavGraphBuilder.userProfile(
    onEditUserProfileButton: () -> Unit
) {
    composable("userProfile") {
        UserProfileScreen(
            onEditUserProfileButton = {
                onEditUserProfileButton()
            }
        )
    }
}

fun NavController.navigateToUserProfile() {
    navigate("userProfile")
}