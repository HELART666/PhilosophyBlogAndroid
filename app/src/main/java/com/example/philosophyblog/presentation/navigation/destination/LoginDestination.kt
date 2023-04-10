package com.example.philosophyblog.presentation.navigation.destination

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.LoginScreen
import com.example.philosophyblog.presentation.viewmodels.LoginViewModel

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