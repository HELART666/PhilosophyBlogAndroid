package com.example.philosophyblog.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenItem(
    val id: String,
    val title: String,
    val icon: ImageVector
) {

    object Home: ScreenItem("posts", "Посты", Icons.Outlined.Home)
    object Profile: ScreenItem("userProfile", "Профиль", Icons.Outlined.Person)
    object Users: ScreenItem("users", "Пользователи", Icons.Outlined.Person)

    object Items{
        val list = listOf(
            Home, Profile, Users
        )
    }

}
