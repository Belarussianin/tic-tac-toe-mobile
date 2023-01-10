package com.belarusianin.tic_tac_toe_mobile.presentation.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.belarusianin.common.presentation.fragment.ComposeFragment
import com.belarusianin.tic_tac_toe_mobile.presentation.settings.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : ComposeFragment<SettingsViewModel>() {

    override val viewModel by viewModel<SettingsViewModel>()

    @Composable
    override fun Content() {
        val darkThemeSettings = viewModel.isDarkTheme.collectAsState(initial = null)
        SettingsFragmentContent(
            viewModel::onNavigateUpClick,
            darkThemeSettings.value,
            viewModel::onDarkThemePreferencesChanged
        )
    }
}

@Composable
fun SettingsFragmentContent(
    onNavigateUp: (() -> Unit)? = null,
    isDarkTheme: Boolean? = null,
    onDarkThemeSettingChange: ((Boolean) -> Unit)? = null
) {
    Column {
        SettingsToolbar(
            navigateUp = onNavigateUp
        )
        SettingsContent(
            isDarkTheme = isDarkTheme,
            onDarkThemeSettingChange = onDarkThemeSettingChange
        )
    }
}