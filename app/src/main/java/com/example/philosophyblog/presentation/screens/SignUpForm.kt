package com.example.philosophyblog.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.philosophyblog.R
import com.example.philosophyblog.utils.TextFieldState

@Composable
fun SignUpForm(
    loginState: TextFieldState,
    emailState: TextFieldState,
    passwordState: TextFieldState,
    repeatPasswordState: TextFieldState,
) {
    EnterTextHeader(text = stringResource(id = R.string.your_login))
    FormTextField(
        placeHolder = stringResource(id = R.string.your_login),
        fieldState = loginState
    )
    EnterTextHeader(text = stringResource(id = R.string.email))
    FormTextField(
        placeHolder = stringResource(id = R.string.email),
        fieldState = emailState
    )
    EnterTextHeader(text = stringResource(id = R.string.password))
    PasswordTextField(
        placeHolder = stringResource(id = R.string.password),
        passwordState = passwordState
    )
    EnterTextHeader(text = stringResource(id = R.string.repeat_password))
    PasswordTextField(
        placeHolder = stringResource(id = R.string.repeat_password),
        passwordState = repeatPasswordState
    )
}