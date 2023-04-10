package com.example.philosophyblog.presentation.screens


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.philosophyblog.R
import com.example.philosophyblog.presentation.ui.theme.PhilosophyBlogTheme
import com.example.philosophyblog.presentation.viewmodels.LoginViewModel
import com.example.philosophyblog.presentation.viewmodels.SignUpViewModel
import com.example.philosophyblog.utils.ScreenState
import com.example.philosophyblog.utils.TextFieldState

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    navToProfile: () -> Unit
) {
    val loginState = remember { TextFieldState() }
    val emailState = remember { TextFieldState() }
    val passwordState = remember { TextFieldState() }
    val repeatPasswordState = remember { TextFieldState() }
    var isRegister = false


    val signUpState = signUpViewModel.signUpResponseLiveData.observeAsState()
    val authState = loginViewModel.authResponseLiveData.observeAsState()


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
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.medium_padding),
                    )
            ) {
                val context = LocalContext.current

                if (signUpState.value is ScreenState.Success && !isRegister) {
                    loginViewModel.sendAuthRequest(
                        login = loginState.text,
                        password = passwordState.text
                    )
                    isRegister = true
                } else {
                    Toast.makeText(context, "${signUpState.value}", Toast.LENGTH_SHORT).show()
                }

                when (authState.value) {
                    is ScreenState.Success -> {
                        // navigate to UserProfileScreen
                        navToProfile()
                    }
                    is ScreenState.Error -> {

                    }
                    is ScreenState.Loading -> {
                        LoadingScreen()
                    }
                    else -> {}
                }

                Header(
                    text = stringResource(id = R.string.header_hello)
                )
                Subtitle(
                    text = stringResource(id = R.string.subtitle_enter_your_data_sign_in)
                )
                SignUpForm(
                    loginState = loginState,
                    emailState = emailState,
                    passwordState = passwordState,
                    repeatPasswordState = repeatPasswordState
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.primary)
                    ),
                    shape = CircleShape,
                    onClick = {
                        if (passwordState.text == repeatPasswordState.text) {
                            signUpViewModel.sendSignUpRequest(
                                login = loginState.text,
                                email = emailState.text,
                                password = passwordState.text
                            )
                        } else {
                            Toast.makeText(
                                context,
                                "Пароли не совпадают!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 36.dp,
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