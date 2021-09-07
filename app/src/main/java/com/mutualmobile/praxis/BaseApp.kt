package com.mutualmobile.praxis

import android.app.Application
import androidx.databinding.library.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApp : Application() {

    companion object{
        const val BASE_URL = "https://api.icndb.com"
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}