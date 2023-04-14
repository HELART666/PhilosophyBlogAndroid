package com.example.philosophyblog.presentation.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.LoginScreen

fun NavGraphBuilder.login(
    onLoginButtonClick: () -> Unit,
    onSignUpButtonClick: () -> Unit
) {
    composable("login") {
        LoginScreen(
            onLoginButtonClick = onLoginButtonClick,
            onSignUpButtonClick = onSignUpButtonClick
        )
    }
}

fun NavController.navigateToLogin() {
    navigate("login")
}