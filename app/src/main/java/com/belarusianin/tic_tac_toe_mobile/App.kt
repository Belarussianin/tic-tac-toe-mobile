package com.belarusianin.tic_tac_toe_mobile

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.belarusianin.tic_tac_toe_mobile.data.preferences.AppPreferencesRepository
import com.belarusianin.tic_tac_toe_mobile.di.appModules
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    private val settings by inject<AppPreferencesRepository>()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
        CoroutineScope(Dispatchers.IO).launch {
            settings.preferencesFlow.map { it.isDarkTheme }.onEach {
                withContext(Dispatchers.Main) {
                    AppCompatDelegate.setDefaultNightMode(if (it) MODE_NIGHT_YES else MODE_NIGHT_NO)
                }
            }.collect()
        }
    }
}