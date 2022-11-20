package com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.view

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.belarusianin.tic_tac_toe_mobile.R

@Composable
fun Toolbar(
    title: String = stringResource(id = R.string.app_name),
    navigateUp: (() -> Unit)? = null,
    settingsClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = if (navigateUp != null) {
            {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrow_back)
                    )
                }
            }
        } else null,
        actions = {
            if (settingsClick != null) {
                IconButton(onClick = settingsClick) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(id = R.string.settings)
                    )
                }
            }
        }
    )
}