package com.example.philosophyblog.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SelectedItemState {
    var text: String by mutableStateOf("")
}