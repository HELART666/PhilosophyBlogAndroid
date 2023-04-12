package com.example.philosophyblog.presentation.navigation.destination

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.EditUserProfileScreen
import com.example.philosophyblog.presentation.viewmodels.UserProfileViewModel

fun NavGraphBuilder.editUserProfile(
    navController: NavController
) {
    composable("editUserProfile") { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("userProfile")
        }
        val parentViewModel = hiltViewModel<UserProfileViewModel>(parentEntry)
        EditUserProfileScreen(
            userProfileViewModel = parentViewModel
        )
    }
}

fun NavController.navigateToEditUserProfile() {
    navigate("editUserProfile")
}