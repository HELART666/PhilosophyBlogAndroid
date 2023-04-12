package com.example.philosophyblog.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SliderState {
    var value: Float by mutableStateOf(0F)
}