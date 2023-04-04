package com.example.philosophyblog.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.philosophyblog.R

@Preview
@Composable
fun LoginForm() {
    Column(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.small_padding),
            )
    ) {
        EnterTextHeader(text = stringResource(id = R.string.login))
        LoginTextField(placeHolder = stringResource(id = R.string.login))
        EnterTextHeader(text = stringResource(id = R.string.password))
        PasswordTextField(placeHolder = stringResource(id = R.string.password))
        CheckBoxRememberMe()
    }
}

@Composable
fun EnterTextHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.small_padding)
            )
    )
}

@Composable
fun LoginTextField(placeHolder: String) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = colorResource(id = R.color.primary),
        backgroundColor = colorResource(id = R.color.primary).copy(alpha = 0.4f)
    )

    var text by remember { mutableStateOf(TextFieldValue("")) }
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
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
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.small_padding)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.primary),
                unfocusedIndicatorColor = colorResource(id = R.color.primary),
                cursorColor = colorResource(R.color.primary)
            )
        )
    }
}

@Composable
fun PasswordTextField(placeHolder: String) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = colorResource(id = R.color.primary),
        backgroundColor = colorResource(id = R.color.primary).copy(alpha = 0.4f)
    )
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    val icon = if (passwordVisibility) {
        painterResource(id = R.drawable.ic_password_visible)
    } else {
        painterResource(id = R.drawable.ic_password_invisible)
    }

    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            visualTransformation = if (!passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon",
                        tint = colorResource(id = R.color.primary)
                    )
                }
            },
            placeholder = {
                Text(
                    text = placeHolder,
                    color = colorResource(id = R.color.disable_gray)
                )
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.small_padding)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.primary),
                unfocusedIndicatorColor = colorResource(id = R.color.primary),
                cursorColor = colorResource(R.color.primary)
            )
        )
    }


}

@Composable
fun CheckBoxRememberMe() {
    var checked by remember {
        mutableStateOf(false)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
            },
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(id = R.color.primary),
                uncheckedColor = colorResource(id = R.color.primary),
            ),
        )
        Text(
            text = stringResource(id = R.string.remember_me),
            style = MaterialTheme.typography.body1
        )
    }
}