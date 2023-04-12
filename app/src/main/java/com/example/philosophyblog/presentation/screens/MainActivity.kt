package com.example.philosophyblog.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.philosophyblog.presentation.navigation.PhilosophyBlogNavigation
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme
import com.example.philosophyblog.presentation.viewmodels.LoginViewModel
import com.example.philosophyblog.utils.ScreenItem
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val currentScreen = mutableStateOf<ScreenItem>(ScreenItem.Profile)
        setContent {
            PhilosophyBlogTheme {
                val navController = rememberNavController()

                TransparentSystemBars()

                PhilosophyBlogNavigation(
                    navController = navController
                )

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

@Composable
fun CoilImage(
    imageUrl: String,
    modifier: Modifier,
    size: Int,
) {
    Box(
        modifier = Modifier
            .size(size.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .memoryCachePolicy(
                    CachePolicy.DISABLED
                )
                .build(),
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}

