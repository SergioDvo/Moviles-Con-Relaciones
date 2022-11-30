package com.example.listas

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
@HiltAndroidApp
class AppTimber: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}