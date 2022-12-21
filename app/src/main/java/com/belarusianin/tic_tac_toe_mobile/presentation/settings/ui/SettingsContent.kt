package com.belarusianin.tic_tac_toe_mobile.presentation.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.belarusianin.common.presentation.component.LoadingScreen
import com.belarusianin.tic_tac_toe_mobile.presentation.settings.viewmodel.SettingsViewModel

@Composable
fun SettingsContent(
    viewModel: SettingsViewModel
) {
    val darkThemeSetting = viewModel.isDarkTheme.collectAsState(initial = null)

    when (val isDarkTheme = darkThemeSetting.value) {
        null -> LoadingScreen()
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = "Theme: ${if (isDarkTheme) "Dark" else "Light"}")
                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = viewModel::onDarkThemePreferencesChanged
                    )
                }
            }
        }
    }
}