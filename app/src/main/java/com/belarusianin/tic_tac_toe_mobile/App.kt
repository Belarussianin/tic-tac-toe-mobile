package com.belarusianin.tic_tac_toe_mobile

import android.app.Application
import com.belarusianin.tic_tac_toe_mobile.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }
}