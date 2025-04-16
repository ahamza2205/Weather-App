package com.example.weatherapp

import android.Manifest
import android.app.Application
import androidx.annotation.RequiresPermission
import com.example.core.utils.NetworkUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : Application(){
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun onCreate() {
        super.onCreate()
        NetworkUtils.observeNetwork(this)
    }
}