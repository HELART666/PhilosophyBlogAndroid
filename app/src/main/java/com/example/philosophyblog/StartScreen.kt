package com.example.philosophyblog

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.philosophyblog.ui.theme.PhilosophyBlogTheme

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun StartScreen() {
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
                LoginForm()
            }
        }
    }
}

@Composable
fun NavBackToolbar() {
    TopAppBar(
        title = {},
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
