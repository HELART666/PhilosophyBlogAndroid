package com.example.philosophyblog.presentation.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.LoginScreen

fun NavGraphBuilder.login(
    onLoginButtonClick: () -> Unit
) {
    composable("login") {
        LoginScreen(
            onLoginButtonClick = onLoginButtonClick
        )
    }
}