package com.example.feature_forecast.presentation.viewmodel

sealed class ForecastIntent {
    data class LoadForecast(val city: String) : ForecastIntent()
}