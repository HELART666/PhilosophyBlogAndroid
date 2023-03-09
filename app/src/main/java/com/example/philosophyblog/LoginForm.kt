package com.example.philosophyblog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun LoginForm() {
    Column() {
        EnterTextHeader(text = stringResource(id = R.string.login))
        LoginTextField(placeHolder = stringResource(id = R.string.login))
        EnterTextHeader(text = stringResource(id = R.string.password))
        PasswordTextField(placeHolder = stringResource(id = R.string.password))
    }
}

@Composable
fun EnterTextHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
    )
}

@Composable
fun LoginTextField(placeHolder: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        textStyle = MaterialTheme.typography.body1,
        onValueChange = {
            text = it
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        placeholder = {
            Text(
                text = placeHolder,
                color = colorResource(id = R.color.disable_gray)
            )
        }
    )
}

@Composable
fun PasswordTextField(placeHolder: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        placeholder = {
            Text(
                text = placeHolder,
                color = colorResource(id = R.color.disable_gray)
            )
        }
    )
}