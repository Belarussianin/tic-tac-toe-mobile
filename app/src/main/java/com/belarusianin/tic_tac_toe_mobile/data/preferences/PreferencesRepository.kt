package com.belarusianin.tic_tac_toe_mobile.data.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

abstract class PreferencesRepository<T>(
    protected val dataStore: DataStore<Preferences>
) {
    protected abstract val TAG: String

    /**
     * Get the user preferences flow.
     */
    val preferencesFlow: Flow<T> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapPreferences(preferences)
        }

    abstract suspend fun updatePreferences(newPreferences: T)

    suspend fun fetchInitialPreferences() = mapPreferences(dataStore.data.first().toPreferences())

    protected abstract fun mapPreferences(preferences: Preferences): T
}