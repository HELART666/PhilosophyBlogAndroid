package com.example.philosophyblog.presentation.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.philosophyblog.presentation.screens.SignUpScreen

fun NavGraphBuilder.signUp(
    navToProfile: () -> Unit
) {
    composable("signUp") {
        SignUpScreen(
            navToProfile = navToProfile
        )
    }
}

fun NavController.navigateToSignUp() {
    navigate("signUp")
}