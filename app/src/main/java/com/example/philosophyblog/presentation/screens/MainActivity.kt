package com.example.philosophyblog.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.core.view.WindowCompat
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme
import com.example.philosophyblog.presentation.viewmodels.LoginViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            PhilosophyBlogTheme {
                TransparentSystemBars()
                LoginScreen(loginViewModel = loginViewModel)
                //HomeScreen()
            }
        }
    }
}

private val BlackScrim = Color(0f, 0f, 0f, 0.3f) // 30% opaque black

@Composable
fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons,
            isNavigationBarContrastEnforced = false,
            transformColorForLightContent = { original ->
                BlackScrim.compositeOver(original)
            }
        )
    }
}

