package com.example.feature_currentweather.presentation

import com.example.core.serveice.models.CurrentWeather

data class CurrentWeatherState(
    val isLoading: Boolean = false,
    val weather: CurrentWeather? = null,
    val error: String? = null
)
