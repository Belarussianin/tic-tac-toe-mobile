package com.belarusianin.tic_tac_toe_mobile.presentation.settings.viewmodel

import androidx.lifecycle.viewModelScope
import com.belarusianin.common.presentation.navigation.NavEvent
import com.belarusianin.common.presentation.viewmodel.BaseViewModel
import com.belarusianin.tic_tac_toe_mobile.data.preferences.AppPreferences
import com.belarusianin.tic_tac_toe_mobile.data.preferences.AppPreferencesRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settings: AppPreferencesRepository
) : BaseViewModel() {

    val isDarkTheme = settings.preferencesFlow.map { it.isDarkTheme }

    fun onDarkThemePreferencesChanged(newPreference: Boolean) =
        viewModelScope.launch { settings.updatePreferences(AppPreferences(newPreference)) }

    fun onNavigateUpClick() {
        navigate(NavEvent.Up)
    }
}