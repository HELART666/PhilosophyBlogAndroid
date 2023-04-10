package com.example.philosophyblog.presentation.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.EditUserProfileScreen

fun NavGraphBuilder.editUserProfile() {
    composable("editUserProfile") {
        EditUserProfileScreen()
    }
}

fun NavController.navigateToEditUserProfile() {
    navigate("editUserProfile")
}