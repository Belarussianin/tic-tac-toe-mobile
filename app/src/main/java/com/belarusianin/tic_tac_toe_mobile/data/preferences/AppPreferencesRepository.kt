package com.belarusianin.tic_tac_toe_mobile.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit

/**
 * Class that handles saving and retrieving app preferences
 */
class AppPreferencesRepository(dataStore: DataStore<Preferences>) :
    PreferencesRepository<AppPreferences>(dataStore) {

    override val TAG: String = "AppPreferencesRepo"

    companion object {
        const val APP_PREFERENCES_NAME: String = "app_settings"
    }

    private object PreferencesKeys {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
    }

    override suspend fun updatePreferences(newPreferences: AppPreferences) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DARK_THEME] = newPreferences.isDarkTheme
        }
    }

    override fun mapPreferences(preferences: Preferences): AppPreferences {
        val darkTheme = preferences[PreferencesKeys.DARK_THEME] ?: false
        return AppPreferences(darkTheme)
    }
}