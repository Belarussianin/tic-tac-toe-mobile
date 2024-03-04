package com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.belarusianin.tic_tac_toe_mobile.R
import com.google.accompanist.themeadapter.material.MdcTheme

@Composable
fun TicTacToeToolbar(
    xCounterValue: Int = 0,
    oCounterValue: Int = 0,
    drawCounterValue: Int = 0,
    navigateUp: (() -> Unit)? = null,
    settingsClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Counter(id = R.string.x_wins_counter_text, value = xCounterValue)
                Counter(id = R.string.o_wins_counter_text, value = oCounterValue)
                Counter(id = R.string.draws_counter_text, value = drawCounterValue)
            }
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
        actions = {
            if (settingsClick != null) {
                IconButton(onClick = settingsClick) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(id = R.string.settings)
                    )
                }
            }
        },
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        elevation = 1.dp
    )
}

@Composable
private fun Counter(@StringRes id: Int, value: Int) {
    TextButton(
        modifier = Modifier
            .border(1.dp, Color.Black)
            .clickable(false) {}, onClick = {}
    ) {
        Text(
            text = stringResource(id = id, value),
            color = Color.Black,
            fontSize = 18.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun TicTacToeToolbarPreview() {
    MdcTheme {
        TicTacToeToolbar()
    }
}
