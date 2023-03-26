package com.example.philosophyblog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.unit.dp

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
        },
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.small_padding)
            ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = colorResource(id = R.color.primary),
            unfocusedIndicatorColor = colorResource(id = R.color.primary)
        )
    )
}

@Composable
fun PasswordTextField(placeHolder: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    val icon = if (passwordVisibility) {
        painterResource(id = R.drawable.ic_password_visible)
    } else {
        painterResource(id = R.drawable.ic_password_invisible)
    }
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
            unfocusedIndicatorColor = colorResource(id = R.color.primary)
        )
    )
}

@Composable
fun CheckBoxRememberMe() {
    var checked by remember {
        mutableStateOf(false)
    }
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
}