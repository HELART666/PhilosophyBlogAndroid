package com.example.philosophyblog.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.philosophyblog.R

@Composable
fun AppToolbar() {
    TopAppBar(
        contentPadding = WindowInsets.systemBars
            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
            .asPaddingValues(),
        backgroundColor = colorResource(id = R.color.white_background),
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "PBLogo"
                    )
                }
                Text(
                    text = stringResource(id = R.string.philosophy),
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.small_padding)
                        ),
                    color = colorResource(id = R.color.primary)
                )
                Text(
                    text = stringResource(id = R.string.blog),
                    color = colorResource(id = R.color.primary_second)
                )
            }
        }
    }
}