package com.example.data.cache

import android.content.SharedPreferences
import javax.inject.Inject

class CityInputCacheImpl @Inject constructor(
    private val prefs: SharedPreferences
) : CityInputCache {

    override fun saveCity(city: String) {
        prefs.edit().putString("last_city", city).apply()
    }

    override fun getLastCity(): String? {
        return prefs.getString("last_city", null)
    }
}
