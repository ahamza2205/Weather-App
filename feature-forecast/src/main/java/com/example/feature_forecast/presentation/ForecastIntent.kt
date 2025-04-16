package com.example.feature_forecast.presentation

sealed class ForecastIntent {
    data class LoadForecast(val city: String) : ForecastIntent()
}