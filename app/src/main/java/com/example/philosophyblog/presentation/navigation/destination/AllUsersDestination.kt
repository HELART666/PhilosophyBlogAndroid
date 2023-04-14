package com.example.philosophyblog.presentation.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.AllUsersScreen

fun NavGraphBuilder.allUsers() {
    composable("allUsers") {
        AllUsersScreen()
    }
}

fun NavController.navigateToAllUsersList() {
    navigate("allUsers")
}