package com.example.core.serveice.models

data class CurrentWeather(
    val country: String,
    val city: String,
    val temperature: Int,
    val condition: String,
    val iconUrl: String,
    val localTime: String,
    val humidity: Int,
    val wind_kph: Int
)
