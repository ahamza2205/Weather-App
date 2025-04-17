package com.example.feature_forecast.presentation.viewmodel

import Forecast

data class ForecastState(
    val forecast: Forecast? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)