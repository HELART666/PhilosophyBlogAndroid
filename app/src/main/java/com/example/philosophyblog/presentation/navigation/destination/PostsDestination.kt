package com.example.philosophyblog.presentation.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.PostScreen

fun NavGraphBuilder.posts() {
    composable("posts") {
        PostScreen()
    }
}

fun NavController.navigateToPosts() {
    navigate("posts")
}