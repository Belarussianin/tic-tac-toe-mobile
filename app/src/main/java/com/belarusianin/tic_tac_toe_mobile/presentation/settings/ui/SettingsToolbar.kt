package com.belarusianin.tic_tac_toe_mobile.presentation.settings.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.belarusianin.tic_tac_toe_mobile.R

@Composable
fun SettingsToolbar(
    title: String = stringResource(id = R.string.settings),
    navigateUp: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                color = Color.Black,
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = if (navigateUp != null) {
            {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrow_back)
                    )
                }
            }
        } else null,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        elevation = 1.dp
    )
}