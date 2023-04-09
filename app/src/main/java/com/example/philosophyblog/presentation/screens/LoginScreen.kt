package com.example.philosophyblog.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.philosophyblog.R
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme
import com.example.philosophyblog.presentation.viewmodels.LoginViewModel
import com.example.philosophyblog.utils.TextFieldState

//@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginButtonClick: () -> Unit
) {
    var loginState = remember { TextFieldState() }
    var passwordState = remember { TextFieldState() }

    PhilosophyBlogTheme {
        Column(
            modifier = Modifier
                .background(
                    color = colorResource(
                        id = R.color.white_background
                    ),
                )
                .padding(
                    top = dimensionResource(id = R.dimen.header_padding)
                )
                .fillMaxHeight()
        ) {
            NavBackToolbar()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.medium_padding),
                    )
            ) {
                Header(text = stringResource(id = R.string.header_hello))
                Subtitle(text = stringResource(id = R.string.subtitle_enter_your_data))
                LoginForm(
                    loginState = loginState,
                    passwordState = passwordState
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight(),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.primary)
                        ),
                        shape = CircleShape,
                        onClick = {
                            loginViewModel.sendAuthRequest(
                                login = loginState.text,
                                password = passwordState.text
                            )
                            //onLoginButtonClick()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 36.dp
                            ),
                    ) {
                        Text(
                            text = stringResource(id = R.string.log_in),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(
                                    vertical = 18.dp,
                                    horizontal = 16.dp
                                ),
                            style = MaterialTheme.typography.body1,
                            color = colorResource(id = R.color.white)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NavBackToolbar(title: String = "") {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = {},
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_go_back_arrow),
                    contentDescription = "go back nav button"
                )
            }
        },
        backgroundColor = colorResource(id = R.color.white_background),
        elevation = 0.dp,
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.smallest_padding)
            )
    )
}

@Composable
fun Header(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h3,
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.header_padding)
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
                top = dimensionResource(id = R.dimen.medium_padding)
            )
    )
}
