package com.example.philosophyblog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen() {
    Column(modifier = Modifier
        .padding(
            start = 24.dp,
        )
    ) {
        Header(text = stringResource(id = R.string.header_hello))
        Subtitle(text = stringResource(id = R.string.subtitle_enter_your_data))
        LoginForm()
    }
}


@Composable
fun Header(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h3,
        modifier = Modifier
            .padding(
                top = 40.dp
            )
    )
}



@Composable
fun Subtitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier
            .padding(
                top = 16.dp
            )
    )
}
